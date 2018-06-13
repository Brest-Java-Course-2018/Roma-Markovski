package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.client.ServerDataAccessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    @ExceptionHandler(ServerDataAccessException.class)
    public String handleServerDataAccessException(
            Exception e, final Model model) {
        LOGGER.debug("handleServerDataAccessException({}, {})", e, model);
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleAnyException(
            Exception e, final Model model) {
        LOGGER.debug("handleAnyException({}, {})", e, model);
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
