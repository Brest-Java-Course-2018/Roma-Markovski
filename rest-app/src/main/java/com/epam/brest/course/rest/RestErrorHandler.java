package com.epam.brest.course.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestErrorHandler {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Unknown error occured.")
    public @ResponseBody String handleAnyException(
            Exception e) {
        LOGGER.error("handleAnyException({})", e);
        return "exception";
    }
}
