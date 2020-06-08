package com.nazim.endpoints;

import com.nazim.models.CarInfoPayload;
import com.nazim.service.CarInfoService;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.nazim.constants.LoggingConstants.*;
import static com.nazim.config.EndpointsConfig.*;

@Component
@Path("/cars")
public class CarInfoEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(CarInfoEndpoint.class);

    @Autowired
    private CarInfoService carInfoService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public void getCarInfos(@Suspended AsyncResponse response) {
        LOG.debug(GET_CAR_INFOS);
        response.setTimeout(ENDPOINT_RESPONSE_TIMEOUT, TimeUnit.MILLISECONDS);

        carInfoService.getCarInfos().subscribe(carInfos -> {
            response.resume(Response.ok(carInfos).build());
        }, err -> {
            LOG.error(ERROR_FETCH_CAR_INFOS, err.getMessage());
            response.resume(err);
        });
    }

    @GET
    @Path("/{carId}")
    @Produces({MediaType.APPLICATION_JSON})
    public void getCarInfo(@Suspended AsyncResponse response,
                           @PathParam("carId") final String carId) {
        LOG.debug(GET_CAR_INFO);
        response.setTimeout(ENDPOINT_RESPONSE_TIMEOUT, TimeUnit.MILLISECONDS);

        carInfoService.getCarInfo(carId).subscribe(carInfo -> {
            response.resume(Response.ok(carInfo).build());
        }, err -> {
            LOG.error(ERROR_FETCH_CAR_INFO, err.getMessage());
            response.resume(err);
        });
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public void createNewCarInfo(@Suspended AsyncResponse response,
                                 @NotNull final CarInfoPayload carInfoPayload) {
        LOG.debug(CREATE_CAR_INFO);
        response.setTimeout(ENDPOINT_RESPONSE_TIMEOUT, TimeUnit.MILLISECONDS);

        carInfoService.createCarInfo(carInfoPayload).subscribe(carInfo -> {
            response.resume(Response.ok(carInfo).status(HttpStatus.CREATED.value()).build());
        }, err -> {
            LOG.error(ERROR_CREATE_CAR_INFO, err.getMessage());
            response.resume(err);
        });
    }

    @PUT
    @Path("/{carId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public void updateCarInfo(@Suspended AsyncResponse response,
                                 @PathParam("carId") final String carId,
                                 @NotNull final Map<String, Object> carInfoUpdatePayload) {
        LOG.debug(UPDATE_CAR_INFO);
        response.setTimeout(ENDPOINT_RESPONSE_TIMEOUT, TimeUnit.MILLISECONDS);

        carInfoService.updateCarInfo(carId, carInfoUpdatePayload).observeOn(Schedulers.io()).subscribe(success -> {
            response.resume(Response.accepted().build());
        }, err -> {
            LOG.error(ERROR_UPDATE_CAR_INFO, err.getMessage());
            response.resume(err);
        });
    }

    @DELETE
    @Path("/{carId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public void deleteCarInfo(@Suspended AsyncResponse response,
                              @PathParam("carId") final String carId) {
        LOG.debug(DELETE_CAR_INFO);
        response.setTimeout(ENDPOINT_RESPONSE_TIMEOUT, TimeUnit.MILLISECONDS);

        carInfoService.deleteCarInfo(carId).subscribe(status -> {
            response.resume(Response.accepted().build());
        }, err -> {
            LOG.error(ERROR_DELETE_CAR_INFO, err.getMessage());
            response.resume(err);
        });
    }
}
