package com.nazim.endpoints;

import com.nazim.service.CarRatingsService;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.nazim.config.EndpointsConfig.ENDPOINT_RESPONSE_TIMEOUT;
import static com.nazim.constants.LoggingConstants.*;

@Component
@Path("/cars/{carId}/ratings")
public class CarRatingsEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(CarRatingsEndpoint.class);

    @Autowired
    private CarRatingsService carRatingsService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public void getCarRatings(@Suspended AsyncResponse response,
                            @PathParam("carId") final String carId) {
        LOG.debug(GET_CAR_RATINGS, carId);
        response.setTimeout(ENDPOINT_RESPONSE_TIMEOUT, TimeUnit.MILLISECONDS);

        carRatingsService.getCarRatings(carId).subscribe(carRatings -> {
            response.resume(Response.ok(carRatings).build());
        }, err -> {
            LOG.error(ERROR_FETCH_CAR_RATINGS, carId, err.getMessage());
            response.resume(err);
        });
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public void createCarRating(@Suspended AsyncResponse response,
                                @PathParam("carId") final String carId,
                                @NotNull final Map<String, Double> carRatingPayload) {
        LOG.debug(CREATE_CAR_RATING, carId);
        response.setTimeout(ENDPOINT_RESPONSE_TIMEOUT, TimeUnit.MILLISECONDS);

        carRatingsService.createCarRating(carId, carRatingPayload).subscribe(carRatings -> {
            response.resume(Response.ok(carRatings).build());
            carRatingsService.notifyAverageCarRating(carId);
        }, err -> {
            LOG.error(ERROR_CREATE_CAR_RATING, carId, err.getMessage());
            response.resume(err);
        });
    }

    @GET
    @Path("/{ratingId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public void getCarRatingById(@Suspended AsyncResponse response,
                                @PathParam("carId") final String carId,
                                @PathParam("ratingId") final String ratingId) {
        LOG.debug(GET_CAR_RATING, ratingId, carId);
        response.setTimeout(ENDPOINT_RESPONSE_TIMEOUT, TimeUnit.MILLISECONDS);

        carRatingsService.getCarRatingById(carId, ratingId).observeOn(Schedulers.io()).subscribe(carRating -> {
            response.resume(Response.ok(carRating).build());
        }, err -> {
            LOG.error(ERROR_FETCH_CAR_RATING, ratingId, carId, err.getMessage());
            response.resume(err);
        });
    }


    @PUT
    @Path("/{ratingId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public void updateCarRating(@Suspended AsyncResponse response,
                                @PathParam("carId") final String carId,
                                @PathParam("ratingId") final String ratingId,
                                @NotNull final Map<String, Double> carRatingUpdatePayload) {
        LOG.debug(UPDATE_CAR_RATING, ratingId, carId);
        response.setTimeout(ENDPOINT_RESPONSE_TIMEOUT, TimeUnit.MILLISECONDS);

        carRatingsService.updateCarRating(carId, ratingId, carRatingUpdatePayload).observeOn(Schedulers.io()).subscribe(success -> {
            response.resume(Response.accepted().build());
            carRatingsService.notifyAverageCarRating(carId);
        }, err -> {
            LOG.error(ERROR_UPDATE_CAR_RATINGS, ratingId, carId, err.getMessage());
            response.resume(err);
        });
    }

    @DELETE
    @Path("/{ratingId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public void deleteCarInfo(@Suspended AsyncResponse response,
                              @PathParam("carId") final String carId,
                              @PathParam("ratingId") final String ratingId) {
        response.setTimeout(ENDPOINT_RESPONSE_TIMEOUT, TimeUnit.MILLISECONDS);

        LOG.debug(DELETE_CAR_RATING, ratingId, carId);
        carRatingsService.deleteCarRating(carId, ratingId).subscribe(status -> {
            response.resume(Response.accepted().build());
            carRatingsService.notifyAverageCarRating(carId);
        }, err -> {
            LOG.error(ERROR_DELETE_CAR_RATINGS, ratingId, carId, err.getMessage());
            response.resume(err);
        });
    }
}
