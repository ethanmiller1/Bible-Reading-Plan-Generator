package com.bibleplangenerator.response;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.concurrent.CompletionException;

public class ObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper {

    private static final com.fasterxml.jackson.databind.ObjectMapper objectMapper;

    static {
        objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
    }

    public static Response readValue(String content) {
        try {
            return objectMapper.readValue(content, new TypeReference<Response>() {
            });
        } catch (IOException ioe) {
            throw new CompletionException(ioe);
        }
    }

}
