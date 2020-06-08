package com.nazim.repository;

import com.nazim.model.CarAverageRating;
import com.nazim.model.CarRating;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.nazim.constants.MongoConstants.*;
import static com.nazim.constants.PayloadConstants.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * Implements car ratings repository.
 */
@Component
@Repository
public class MongoCarRatingsRepository implements CarRatingsRepository {

    private final int VERSION_INCREMENT = 1;

    @Autowired
    private Environment env;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Observable<List<CarRating>> getCarRatings(final String carId) {
        final String collectionName = getCollectionName();
        final Sort sort = getSortOrder();
        final Query query = new Query().query(Criteria.where(CAR_ID_FIELD).is(carId)).with(sort);
        return Observable.fromCallable(() -> mongoTemplate.find(query, CarRating.class, collectionName)).subscribeOn(Schedulers.computation());
    }

    @Override
    public Observable<CarRating> createCarRating(final CarRating carRating) {
        final String collectionName = getCollectionName();
        return Observable.fromCallable(() -> mongoTemplate.insert(carRating, collectionName)).subscribeOn(Schedulers.computation());
    }

    @Override
    public Observable<Optional<CarRating>> getCarRatingById(final String carId, final String ratingId) {
        final String collectionName = getCollectionName();
        final Query query = Query.query(Criteria.where(ID_FIELD).is(ratingId).and(CAR_ID_FIELD).is(carId));
        return Observable.fromCallable(() -> Optional.ofNullable(mongoTemplate.findOne(query, CarRating.class, collectionName))).subscribeOn(Schedulers.computation());
    }

    @Override
    public Observable<Boolean> updateCarRating(final String carId, final String ratingId, final Map<String, Double> carRatingPayload) {
        final String collectionName = getCollectionName();
        final Query query = Query.query(Criteria.where(ID_FIELD).is(ratingId).and(CAR_ID_FIELD).is(carId));
        final Update update = new Update();
        update.set(RATING_FIELD, carRatingPayload.get(PAYLOAD_RATING_ATTR_KEY))
                .set(MODIFIED_AT_FIELD, Instant.now())
                .inc(VERSION_FIELD, VERSION_INCREMENT);

        return Observable.fromCallable(() -> mongoTemplate.updateFirst(query, update, collectionName))
                .map(result -> result.getMatchedCount() > 0)
                .subscribeOn(Schedulers.computation());
    }

    @Override
    public Observable<Boolean> deleteCarRating(final String carId, final String ratingId) {
        final String collectionName = getCollectionName();
        final Query query = Query.query(Criteria.where(ID_FIELD).is(ratingId).and(CAR_ID_FIELD).is(carId));
        return Observable.fromCallable(() -> mongoTemplate.remove(query, CarRating.class, collectionName))
                .map(t -> t.getDeletedCount() > 0).subscribeOn(Schedulers.computation());
    }

    @Override
    public CarAverageRating getCarAverageRating(final String carId) {
        MatchOperation filterCriteria = match(Criteria.where(CAR_ID_FIELD).is(carId));
        GroupOperation groupByCar = group()
                .avg(RATING_FIELD)
                .as(AVERAGE_RATING_ATTRIBUTE).first(CAR_ID_FIELD).as(CAR_ID_FIELD);
        Aggregation aggregation = newAggregation(filterCriteria, groupByCar);
        AggregationResults<CarAverageRating> result = mongoTemplate.aggregate(aggregation, getCollectionName(), CarAverageRating.class);
        return result.getUniqueMappedResult();
    }

    private Sort getSortOrder() {
        return Sort.by(Sort.Direction.ASC, MODIFIED_AT_FIELD);
    }

    private String getCollectionName() {
        return env.getProperty(COLLECTION_NAME_PREFIX_ENV_KEY) + COLLECTION_NAME_DELIMITER  + COLLECTION_RESOURCE_NAME;
    }
}
