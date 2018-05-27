package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dto.WriterDTO;
import com.epam.brest.course.model.Writer;
import com.epam.brest.course.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Controller
public class WriterController {

    @Autowired
    WriterService writerService;

    @GetMapping(value = "/writers")
    public String getWriterDTOs(Model model) {
        Collection<WriterDTO> writers = writerService.getWriterDTOs();
        model.addAttribute("writers", writers);
        return "writers";
    }

    @GetMapping(value = "/writer/{id}")
    public String getWriterById(@PathVariable Integer id, Model model) {
        Writer writer = writerService.getWriterById(id);
        model.addAttribute("writer", writer);
        return "writer";
    }
}
