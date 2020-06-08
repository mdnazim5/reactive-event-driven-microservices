package com.nazim.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nazim.model.CarAverageRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.nazim.constants.LoggingConstants.*;

@Component
public class RabbitMQEventPublisherService implements EventPublisherService {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQEventPublisherService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${queue.average.ratings.request.name}")
    private String carAvgRatingQueueRequestName;

    private final ObjectMapper mapper = new ObjectMapper();

    public void publishCarAvgRating(final CarAverageRating carAverageRatingEventPayload) {
        carAverageRatingEventPayload.setAvgRatingPrecision();
        publish(carAvgRatingQueueRequestName, carAverageRatingEventPayload);
    }

    private void publish(final String routingKey, final CarAverageRating carAverageRatingEventPayload) {
        try {
            rabbitTemplate.convertAndSend(routingKey, serializeEventPayload(carAverageRatingEventPayload));
            LOG.info(EVENT_SUCCESS_CAR_AVERAGE_RATING);
        } catch (final AmqpException ae) {
            final String msg = EVENT_ERROR_CAR_AVERAGE_RATING + ae.getMessage();
            LOG.error(msg, ae);
        } catch (final Exception se) {
            LOG.error(EVENT_ERROR_CAR_AVERAGE_RATING + se.getMessage(), se);
        }
    }

    private String serializeEventPayload(final CarAverageRating carAverageRatingEventPayload) {
        try {
            return mapper.writeValueAsString(carAverageRatingEventPayload);
        } catch (JsonProcessingException e) {
            final String msg =  EVENT_ERROR_SERIALIZE_PAYLOAD + e.getMessage();
            LOG.error(msg, e);
            throw new RuntimeException(msg);
        }
    }
}