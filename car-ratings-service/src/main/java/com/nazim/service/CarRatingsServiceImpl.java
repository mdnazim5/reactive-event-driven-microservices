package com.nazim.service;

import com.nazim.exception.RatingNotFoundException;
import com.nazim.model.CarRating;
import com.nazim.publisher.EventPublisherService;
import com.nazim.repository.CarRatingsRepository;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.nazim.constants.ExceptionConstants.*;
import static com.nazim.constants.PayloadConstants.*;

@Component
public class CarRatingsServiceImpl implements CarRatingsService{

    @Autowired
    private CarRatingsRepository carRatingsRepository;

    @Autowired
    private EventPublisherService rabbitEventPublisherService;

    @Override
    public Observable<List<CarRating>> getCarRatings(final String carId) {
        return carRatingsRepository.getCarRatings(carId);
    }

    public Observable<CarRating> createCarRating(final String carId, final Map<String, Double> carRatingPayload) {
        CarRating carRating = new CarRating(carId, carRatingPayload.get(PAYLOAD_RATING_ATTR_KEY));
        return carRatingsRepository.createCarRating(carRating);
    }

    @Override
    public Observable<CarRating> getCarRatingById(final String carId, final String ratingId) {
        return carRatingsRepository.getCarRatingById(carId, ratingId).map(rating -> rating.orElseThrow(() ->
                new RatingNotFoundException(String.format(RATING_NOT_FOUND, ratingId,  carId), HttpStatus.NOT_FOUND.value())));
    }

    @Override
    public Observable<Boolean> updateCarRating(final String carId, final String ratingId, final Map<String, Double> carRatingPayload) {
        return carRatingsRepository.updateCarRating(carId, ratingId, carRatingPayload).map(result -> validateOperationResult(result, carId, ratingId));
    }

    @Override
    public Observable<Boolean> deleteCarRating(final String carId, final String ratingId) {
        return carRatingsRepository.deleteCarRating(carId, ratingId).map(result -> validateOperationResult(result, carId, ratingId));
    }

    @Override
    public void notifyAverageCarRating(final String carId) {
        rabbitEventPublisherService.publishCarAvgRating(carRatingsRepository.getCarAverageRating(carId));
    }

    private Boolean validateOperationResult(final Boolean operationResult, final String carId, final String ratingId) {
        if (!operationResult) {
            throw new RatingNotFoundException(String.format(RATING_NOT_FOUND, ratingId, carId), HttpStatus.NOT_FOUND.value());
        }
        return operationResult;
    }
}
