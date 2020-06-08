package com.nazim.repository;

import com.nazim.model.CarAverageRating;
import com.nazim.model.CarRating;
import io.reactivex.Observable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CarRatingsRepository {

    /**
     * Returns the list of ratings of a given car from MongoDB.
     *
     * @return List of ratings for a given car.
     */
    Observable<List<CarRating>> getCarRatings(final String carId);

    /**
     * Creates a new rating for a given car in MongoDB.
     *
     * @return Details of the newly created rating for the given car.
     */
    Observable<CarRating> createCarRating(final CarRating carRating);

    /**
     * Returns an existing rating for the given car from MongoDB.
     *
     * @return Details of rating for the given car.
     */
    Observable<Optional<CarRating>> getCarRatingById(final String carId, final String ratingId);

    /**
     * Updates an existing rating for the given car in MongoDB.
     *
     * @return Update status.
     */
    Observable<Boolean> updateCarRating(final String carId, final String ratingId, final Map<String, Double> carRatingPayload);

    /**
     * Deletes an existing car rating for a given car in MongoDB.
     *
     * @return Delete status.
     */
    Observable<Boolean> deleteCarRating(final String carId, final String ratingId);

    /**
     * Gets the average rating of a given car.
     *
     * @return Details containing the average rating for the given car.
     */
    CarAverageRating getCarAverageRating(final String carId);
}
