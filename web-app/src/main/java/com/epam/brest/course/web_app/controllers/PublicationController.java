package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicationController {


    @GetMapping(value = "/publications")
    public String writers(Model model) {
        return "publications";
    }

    @GetMapping(value = "/publication")
    public String writer(Model model) {
        return "publication";
    }
}
