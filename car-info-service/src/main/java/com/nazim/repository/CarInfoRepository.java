package com.nazim.repository;

import com.nazim.models.CarInfo;
import io.reactivex.Observable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CarInfoRepository {

    /**
     * Returns list of car information objects from MongoDB.
     *
     * @return List of car information objects.
     */
    Observable<List<CarInfo>> getCarInfos();

    /**
     * Creates a new car information object in MongoDB.
     *
     * @return Details of the newly created car information object.
     */
    Observable<CarInfo> createCarInfo(final CarInfo carInfo);

    /**
     * Updates an existing car information object in MongoDB.
     *
     * @return Update status.
     */
    Observable<Boolean> updateCarInfo(final String carId, final Map<String, Object> carInfoPayload);


    /**
     * Returns the requested car information object from MongoDB.
     *
     * @return Car information object.
     */
    Observable<Optional<CarInfo>> getCarInfo(final String carId);


    /**
     * Deletes an existing car information object from MongoDB.
     *
     * @return Delete status.
     */
    Observable<Boolean> deleteCarInfo(final String carId);

    /**
     * Updates the average rating of the car in MongoDB.
     *
     * @return Update status.
     */
    Observable<Boolean> updateCarAverageRating(final String carId, final Double averageCarRating);
}
