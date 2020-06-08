package com.nazim.repository;


import com.nazim.models.CarInfo;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.nazim.constants.MongoConstants.*;

/**
 * Implements car info repository.
 */
@Component
@Repository
public class MongoCarInfoRepository implements CarInfoRepository{
    private final int VERSION_INCREMENT = 1;

    @Autowired
    private Environment env;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Observable<List<CarInfo>> getCarInfos() {
        final String collectionName = getCollectionName();
        final Sort sort = getSortOrder();
        final Query query = new Query().with(sort);
        return Observable.fromCallable(() -> mongoTemplate.find(query, CarInfo.class, collectionName)).subscribeOn(Schedulers.computation());
    }

    @Override
    public Observable<CarInfo> createCarInfo(final CarInfo carInfo) {
        final String collectionName = getCollectionName();
        return Observable.fromCallable(() -> mongoTemplate.insert(carInfo, collectionName)).subscribeOn(Schedulers.computation());
    }

    @Override
    public Observable<Boolean> updateCarInfo(final String carId, final Map<String, Object> carInfoPayload){
        carInfoPayload.remove(AVG_RATING_FIELD);

        final String collectionName = getCollectionName();
        final Query query = Query.query(Criteria.where(ID_FIELD).is(carId));
        final Update update = new Update();
        carInfoPayload.values().removeIf(Objects::isNull);
        carInfoPayload.forEach(update::set);

        update.set(MODIFIED_AT_FIELD, Instant.now())
        .inc(VERSION_FIELD, VERSION_INCREMENT);

        return Observable.fromCallable(() -> mongoTemplate.updateFirst(query, update, collectionName))
                .map(result -> result.getMatchedCount() > 0)
                .subscribeOn(Schedulers.computation());
    }

    @Override
    public Observable<Optional<CarInfo>> getCarInfo(final String carId) {
        final String collectionName = getCollectionName();
        final Query query = Query.query(Criteria.where(ID_FIELD).is(carId));
        return Observable.fromCallable(() -> Optional.ofNullable(mongoTemplate.findOne(query, CarInfo.class, collectionName))).subscribeOn(Schedulers.computation());
    }

    @Override
    public Observable<Boolean> deleteCarInfo(final String carId){
        final String collectionName = getCollectionName();
        final Query query = Query.query(Criteria.where(ID_FIELD).is(carId));
        return Observable.fromCallable(() -> mongoTemplate.remove(query, CarInfo.class, collectionName))
                .map(t -> t.getDeletedCount() > 0).subscribeOn(Schedulers.computation());
    }

    @Override
    public Observable<Boolean> updateCarAverageRating(final String carId, final Double averageCarRating) {
        final String collectionName = getCollectionName();
        final Query query = Query.query(Criteria.where(ID_FIELD).is(carId));
        final Update update = new Update();
        update.set(AVG_RATING_FIELD, averageCarRating)
                .set(MODIFIED_AT_FIELD, Instant.now())
                .inc(VERSION_FIELD, VERSION_INCREMENT);

        return Observable.fromCallable(() -> mongoTemplate.updateFirst(query, update, collectionName))
                .map(result -> result.getMatchedCount() > 0)
                .subscribeOn(Schedulers.computation());
    }

    private Sort getSortOrder() {
        return Sort.by(Sort.Direction.ASC, MODIFIED_AT_FIELD);
    }

    private String getCollectionName() {
        return env.getProperty(COLLECTION_NAME_PREFIX_ENV_KEY) + COLLECTION_NAME_DELIMITER  + COLLECTION_RESOURCE_NAME;
    }
}
