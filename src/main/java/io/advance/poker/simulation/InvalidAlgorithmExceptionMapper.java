package io.advance.poker.simulation;

import io.advance.poker.simulation.api.InvalidPokerAlgorithmException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidAlgorithmExceptionMapper implements ExceptionMapper<InvalidPokerAlgorithmException> {
    @Override
    public Response toResponse(InvalidPokerAlgorithmException ex) {
        return Response.status(Response.Status.PRECONDITION_FAILED)
                .header(REASON, ex.getMessage())
                .build();
    }
    private static final String REASON = "MESSAGE";
}
