package com.epam.brest.course.client.rest;

import com.epam.brest.course.client.ServerDataAccessException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Custom response error handler.
 */
public class CustomResponseErrorHandler implements ResponseErrorHandler {

    private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    @Override
    public final boolean hasError(
            final ClientHttpResponse response) throws IOException {
        return errorHandler.hasError(response);
    }

    @Override
    public final void handleError(
            final ClientHttpResponse response) throws IOException {
        throw new ServerDataAccessException("ERROR: " + response.getStatusCode()
                + ": " + response.getStatusText()
                + ": " + response.getBody());
    }
}
