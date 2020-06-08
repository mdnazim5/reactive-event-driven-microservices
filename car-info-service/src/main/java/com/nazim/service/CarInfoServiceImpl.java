package com.nazim.service;

import com.nazim.exception.CarNotFoundException;
import com.nazim.models.CarInfo;
import com.nazim.models.CarInfoPayload;
import com.nazim.repository.CarInfoRepository;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.nazim.constants.ExceptionConstants.*;

@Component
public class CarInfoServiceImpl implements CarInfoService {

    @Autowired
    private CarInfoRepository carInfoRepository;

    @Override
    public Observable<List<CarInfo>> getCarInfos() {
        return carInfoRepository.getCarInfos();
    }

    @Override
    public Observable<CarInfo> createCarInfo(final CarInfoPayload carInfoPayload) {
        CarInfo carInfo = CarInfo.getBuilder()
                .setManufacturer(carInfoPayload.getManufacturer())
                .setModel(carInfoPayload.getModel())
                .setPrice(carInfoPayload.getPrice())
                .setColor(carInfoPayload.getColor())
                .setYear(carInfoPayload.getYear())
                .build();
        return carInfoRepository.createCarInfo(carInfo);
    }

    @Override
    public Observable<Boolean> updateCarInfo(final String carId, final Map<String, Object> carInfoPayload){
        return carInfoRepository.updateCarInfo(carId, carInfoPayload).map(result -> validateOperationResult(result, carId));
    }

    @Override
    public Observable<CarInfo> getCarInfo(final String carId) {
        return carInfoRepository.getCarInfo(carId).map(carInfo -> carInfo.orElseThrow(() ->
                new CarNotFoundException(String.format(CAR_NOT_FOUND, carId), HttpStatus.NOT_FOUND.value())));
    }

    @Override
    public Observable<Boolean> deleteCarInfo(final String carId) {
        return carInfoRepository.deleteCarInfo(carId).map(result -> validateOperationResult(result, carId));
    }

    @Override
    public Observable<Boolean> updateCarAverageRating(final String carId, final Double averageCarRating) {
        return carInfoRepository.updateCarAverageRating(carId, averageCarRating).map(result -> validateOperationResult(result, carId));
    }

    private Boolean validateOperationResult(final Boolean operationResult, final String carId) {
        if (!operationResult) {
            throw new CarNotFoundException(String.format(CAR_NOT_FOUND, carId), HttpStatus.NOT_FOUND.value());
        }
        return operationResult;
    }
}
