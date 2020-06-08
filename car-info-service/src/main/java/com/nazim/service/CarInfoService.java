package com.nazim.service;

import com.nazim.models.CarInfo;
import com.nazim.models.CarInfoPayload;
import io.reactivex.Observable;

import java.util.List;
import java.util.Map;

public interface CarInfoService {

    /**
     * Returns list of car information objects.
     *
     * @return List of car information objects.
     */
    Observable<List<CarInfo>> getCarInfos();

    /**
     * Returns the requested car information object.
     *
     * @return Car information object.
     */
    Observable<CarInfo> getCarInfo(final String carId);

    /**
     * Creates a new car information object.
     *
     * @return Details of the new car information object.
     */
    Observable<CarInfo> createCarInfo(final CarInfoPayload carInfoPayload);

    /**
     * Updates an existing car information object.
     *
     * @return Update status.
     */
    Observable<Boolean> updateCarInfo(final String carId, final Map<String, Object> carInfoPayload);

    /**
     * Deletes an existing car information object.
     *
     * @return Delete status.
     */
    Observable<Boolean> deleteCarInfo(final String carId);

    /**
     * Updates the average rating of the car.
     *
     * @return Update status.
     */
    Observable<Boolean> updateCarAverageRating(final String carId, final Double averageCarRating);
}
