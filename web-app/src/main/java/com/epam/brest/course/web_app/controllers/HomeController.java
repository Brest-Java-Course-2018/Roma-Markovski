package com.epam.brest.course.web_app.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class which redirects to home page.
 */
@Controller
public class HomeController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Displays home page.
     * @return view name
     */
    @GetMapping(value = "/")
    public final String defaultPageRedirect() {
        LOGGER.debug("defaultPageRedirect()");
        return "redirect:publications";
    }

}
