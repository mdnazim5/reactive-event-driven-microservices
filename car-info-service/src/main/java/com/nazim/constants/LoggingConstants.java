package com.nazim.constants;

/**
 * Static helper class with logging messages.
 */
public class LoggingConstants {

    private LoggingConstants() {}

    public static final String GET_CAR_INFOS = "Getting all car information";
    public static final String ERROR_FETCH_CAR_INFOS = "Cannot get cars information due to the following error: {}";
    public static final String GET_CAR_INFO = "Getting car information";
    public static final String ERROR_FETCH_CAR_INFO = "Cannot get car information due to the following error: {}";
    public static final String CREATE_CAR_INFO = "Creating new car information";
    public static final String ERROR_CREATE_CAR_INFO = "Cannot create a new car information due to the following error: {}";
    public static final String UPDATE_CAR_INFO = "Updating car information";
    public static final String ERROR_UPDATE_CAR_INFO = "The car information cannot be updated due to the following errors: {}";
    public static final String DELETE_CAR_INFO = "Deleting car information";
    public static final String ERROR_DELETE_CAR_INFO = "Deleting car information";
    public static final String EVENT_PROCESS_CAR_AVERAGE_RATING = "Processing the average rating event request for the car with ID {}";
    public static final String EVENT_SUCCESS_CAR_AVERAGE_RATING = "Successfully processed car average rating event request";
    public static final String EVENT_ERROR_CAR_AVERAGE_RATING = "Cannot process car average rating event request.";
    public static final String EVENT_ERROR_PARSE_PAYLOAD = "Cannot parse the given car average rating payload.";
}
