package com.nazim.config;

import com.nazim.endpoints.CarRatingsEndpoint;
import com.nazim.exception.RatingNotFoundExceptionMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(JacksonFeature.class);
        register(CarRatingsEndpoint.class);
        register(RatingNotFoundExceptionMapper.class);
    }
}
