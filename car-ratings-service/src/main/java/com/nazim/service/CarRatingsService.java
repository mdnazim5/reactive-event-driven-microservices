package com.nazim.service;

import com.nazim.model.CarRating;
import io.reactivex.Observable;

import java.util.List;
import java.util.Map;

public interface CarRatingsService {

    /**
     * Returns the ratings of a given car.
     *
     * @return List of ratings for a given car.
     */
    Observable<List<CarRating>> getCarRatings(final String carId);

    /**
     * Creates a new rating for a given car.
     *
     * @return Details of the newly created rating for the given car.
     */
    Observable<CarRating> createCarRating(final String carId, final Map<String, Double> carRatingPayload);

    /**
     * Returns an existing rating for the given car.
     *
     * @return Requested rating for the given car.
     */
    Observable<CarRating> getCarRatingById(final String carId, final String ratingId);

    /**
     * Updates an existing rating for the given car.
     *
     * @return Update status.
     */
    Observable<Boolean> updateCarRating(final String carId, final String ratingId, final Map<String, Double> carRatingPayload);

    /**
     * Deletes an existing car rating for a given car.
     *
     * @return Delete status.
     */
    Observable<Boolean> deleteCarRating(final String carId, final String ratingId);

    /**
     * Notifies the average rating of a car to other services.
     *
     */
    void notifyAverageCarRating(final String carId);
}
