package com.nazim.config;

import com.nazim.endpoints.CarInfoEndpoint;
import com.nazim.exception.CarNotFoundExceptionMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(JacksonFeature.class);
        register(CarInfoEndpoint.class);
        register(CarNotFoundExceptionMapper.class);
    }
}
