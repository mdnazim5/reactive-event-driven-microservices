package com.nazim.exception;

import com.nazim.models.ErrorMessage;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CarNotFoundExceptionMapper implements ExceptionMapper<CarNotFoundException> {

    @Override
    public Response toResponse(CarNotFoundException e) {
        return Response.status(e.getStatus()).entity(new ErrorMessage(e.getStatus(), e.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }

}
