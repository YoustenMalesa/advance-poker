package io.advance.client;

import com.fasterxml.jackson.jaxrs.base.JsonMappingExceptionMapper;
import com.fasterxml.jackson.jaxrs.base.JsonParseExceptionMapper;

import java.util.logging.Logger;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.ExceptionMapper;

public class ClientBuilderHelper {
    private static final Logger log = Logger.getLogger(ClientBuilderHelper.class.getName());

    public ClientBuilderHelper() {
    }

    public static ClientBuilder createClientBuilder() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        clientBuilder.property("jersey.config.disableMoxyJson.client", true);
        clientBuilder.property("jersey.config.client.disableMoxyJson", true);
        clientBuilder.register(JsonParseExceptionMapper.class, new Class[]{ExceptionMapper.class});
        clientBuilder.register(JsonMappingExceptionMapper.class, new Class[]{ExceptionMapper.class});
        clientBuilder.register(ObjectMapperContextResolver.class, new Class[]{ContextResolver.class});
        return clientBuilder;
    }
}