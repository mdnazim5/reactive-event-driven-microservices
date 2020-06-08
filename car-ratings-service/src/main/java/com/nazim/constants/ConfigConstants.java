package com.nazim.constants;

/**
 * Static helper class with configuration constants.
 */
public class ConfigConstants {

    private ConfigConstants() {};

    public static final String ROUTE_MATCHER_PATTERN = "/**";
    public static final String MONGO_DB_URI_ENV_KEY = "MONGO_DB_URI";
    public static final String MONGO_DB_URI_DEFAULT_VALUE = "mongodb://localhost:27017";
    public static final String MONGO_DB_NAME_ENV_KEY = "MONGO_DB_NAME";
    public static final String MONGO_DB_NAME_DEFAULT_VALUE = "carsDB";
    public static final String RABBIT_MQ_HOST_ENV_KEY = "RABBIT_MQ_HOST";
    public static final String RABBIT_MQ_HOST_DEFAULT_VALUE = "localhost";
    public static final String RABBIT_MQ_USERNAME_ENV_KEY = "RABBIT_MQ_USERNAME";
    public static final String RABBIT_MQ_USERNAME_DEFAULT_VALUE = "guest";
    public static final String RABBIT_MQ_PASSWORD_ENV_KEY = "RABBIT_MQ_PASSWORD";
    public static final String RABBIT_MQ_PASSWORD_DEFAULT_VALUE = "guest";
}
