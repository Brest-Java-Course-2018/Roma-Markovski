package com.epam.brest.course.web_app.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles all the errors.
 */
@ControllerAdvice
public class ErrorHandler {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public final String handleAnyException(
            final Exception e, final Model model) {
        LOGGER.debug("handleAnyException({}, {})", e, model);
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
