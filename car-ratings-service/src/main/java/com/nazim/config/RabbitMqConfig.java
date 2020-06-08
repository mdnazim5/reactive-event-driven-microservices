package com.nazim.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbit.queue.receive.timeout}")
    private Long rabbitQueueReceiveTimeout;

    @Value("${queue.average.ratings.request.name}")
    private String carAvgRatingQueueRequestName;

    @Value("${queue.average.ratings.request.exchange}")
    private String carAvgRatingQueueRequestExchange;

    @Bean
    public Queue carAvgRatingRequestQueue() {
        return new Queue(carAvgRatingQueueRequestName, true);
    }

    @Bean
    public FanoutExchange carAvgRatingRequestExchange() {
        return new FanoutExchange(carAvgRatingQueueRequestExchange);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}