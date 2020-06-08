package com.nazim.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nazim.service.CarInfoService;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

import static com.nazim.constants.LoggingConstants.*;
import static com.nazim.constants.PayloadConstants.*;

@Component
public class CarAverageRatingEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(CarAverageRatingEventListener.class);

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CarInfoService carInfoService;

    @RabbitListener(queues = "${queue.average.ratings.request.name}")
    public void processCarAverageRatingEvent(final Message carAverageRatingMessage) {
        deserializePayloadBody(carAverageRatingMessage.getBody()).flatMap(carAverageRating -> {
            final String carId = (String) carAverageRating.get(PAYLOAD_CAR_ID_ATTR_KEY);
            final Double averageRating = (Double) carAverageRating.get(PAYLOAD_AVG_RATING_ATTR_KEY);
            LOG.debug(EVENT_PROCESS_CAR_AVERAGE_RATING, carId);
            return carInfoService.updateCarAverageRating(carId, averageRating);
        }).subscribe(s -> LOG.debug(EVENT_SUCCESS_CAR_AVERAGE_RATING),
                e -> LOG.error(EVENT_ERROR_CAR_AVERAGE_RATING + e.getMessage())
        );
    }

    private Observable<Map<String, Object>> deserializePayloadBody(final byte[] payload) {
        try {
            return Observable.just(mapper.readValue(payload, new TypeReference<Map<String, Object>>() {
            }));
        } catch (IOException e) {
            final String msg = EVENT_ERROR_PARSE_PAYLOAD + e.getMessage();
            LOG.error(msg);
            return Observable.error(new RuntimeException(msg));
        }
    }
}
