package io.advance.client;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

import javax.inject.Singleton;
import javax.ws.rs.ext.ContextResolver;

@Singleton
public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {
    public ObjectMapperContextResolver() {
    }

    public ObjectMapper getContext(Class<?> type) {
        return createObjectMapper();
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        AnnotationIntrospector primary = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
        AnnotationIntrospectorPair pair = new AnnotationIntrospectorPair(primary, secondary);
        objectMapper.setAnnotationIntrospector(pair);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME, true);
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        return objectMapper;
    }
}