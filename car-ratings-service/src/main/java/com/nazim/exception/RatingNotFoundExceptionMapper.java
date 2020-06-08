package com.nazim.exception;


import com.nazim.model.ErrorMessage;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RatingNotFoundExceptionMapper implements ExceptionMapper<RatingNotFoundException> {

    @Override
    public Response toResponse(RatingNotFoundException e) {
        return Response.status(e.getStatus()).entity(new ErrorMessage(e.getStatus(), e.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }

}
