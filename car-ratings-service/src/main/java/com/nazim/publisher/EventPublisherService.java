package com.nazim.publisher;

import com.nazim.model.CarAverageRating;

public interface EventPublisherService {

    /**
     * Publishes the average car rating to RabbitMQ.
     *
     * @param carAverageRatingEventPayload payload with average car rating.
     */
    void publishCarAvgRating(final CarAverageRating carAverageRatingEventPayload);
}