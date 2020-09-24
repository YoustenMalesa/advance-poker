package io.advance;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    @Override
    public Response toResponse(IllegalArgumentException ex) {
        return Response.status(Response.Status.PRECONDITION_FAILED)
                .header(REASON, ex.getMessage())
                .build();
    }
    private static final String REASON = "MESSAGE";
}
