package com.nazim.constants;

/**
 * Static helper class with logging messages.
 */
public class LoggingConstants {

    private LoggingConstants() {}

    public static final String GET_CAR_RATINGS = "Getting all the ratings for the car with ID {}";
    public static final String ERROR_FETCH_CAR_RATINGS = "Cannot get the ratings for the car with ID {} due to the following error: {}";
    public static final String GET_CAR_RATING = "Getting the rating with ID {} for the car with ID {}";
    public static final String ERROR_FETCH_CAR_RATING = "Cannot get the rating with ID {} for the car with ID {} due to the following error: {}";
    public static final String CREATE_CAR_RATING = "Creating new rating for the car with ID {}";
    public static final String ERROR_CREATE_CAR_RATING = "Cannot create a new rating for the car with ID {} due to the following error: {}";
    public static final String UPDATE_CAR_RATING = "Updating the rating with ID for the car with ID {}";
    public static final String ERROR_UPDATE_CAR_RATINGS = "Cannot update the rating with ID {} for the car with ID {} due to the following error: {}";
    public static final String DELETE_CAR_RATING = "Deleting the rating with ID for the car with ID {}";
    public static final String ERROR_DELETE_CAR_RATINGS = "Cannot delete the rating with ID {} for the car with ID {} due to the following error: {}";
    public static final String EVENT_ERROR_SERIALIZE_PAYLOAD = "Cannot serialize the event payload.";
    public static final String EVENT_SUCCESS_CAR_AVERAGE_RATING = "Message has been published to RabbitMQ.";
    public static final String EVENT_ERROR_CAR_AVERAGE_RATING = "Cannot process car average rating event request.";

}
