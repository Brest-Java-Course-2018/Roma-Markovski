package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dto.WriterDTO;
import com.epam.brest.course.model.Writer;
import com.epam.brest.course.service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping(value = "/writer")
    public String gotoAddWriter(Model model) {
        Writer writer = new Writer();
        model.addAttribute("writer", writer);
        model.addAttribute("isNew", true);
        return "writer";
    }

    @PostMapping(value = "/writer")
    public String addWriter(Writer writer, BindingResult result) {
        if (result.hasErrors()) {
            return "writer";
        }
        else {
            this.writerService.addWriter(writer);
            return "redirect:/writers";
        }
    }

    @GetMapping(value = "/writer/{id}")
    public String gotoEditWriter(@PathVariable Integer id, Model model) {
        Writer writer = writerService.getWriterById(id);
        model.addAttribute("writer", writer);
        model.addAttribute("isNew", false);
        return "writer";
    }

    @PostMapping(value = "/writer/{id}")
    public String editWriter(Writer writer, BindingResult result) {
        if (result.hasErrors()) {
            return "writer";
        }
        else {
            this.writerService.updateWriter(writer);
            return "redirect:/writers";
        }
    }

    @GetMapping(value = "/writer/{id}/delete")
    public String deleteWriterById(@PathVariable Integer id, Model model) {
        writerService.deleteWriterById(id);
        return "redirect:/writers";
    }
}
