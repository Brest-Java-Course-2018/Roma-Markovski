package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.model.Publication;
import com.epam.brest.course.model.Writer;
import com.epam.brest.course.service.PublicationService;
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
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    @Autowired
    WriterService writerService;

    @GetMapping(value = "/publications")
    public String getPublications(Model model) {
        Collection<Publication> publications = publicationService.getPublications();
        model.addAttribute("publications", publications);
        return "publications";
    }

    @GetMapping(value = "/publication")
    public String gotoAddPublication(Model model) {
        Publication publication = new Publication();
        Collection<Writer> writers = writerService.getWriters();
        model.addAttribute("publication", publication);
        model.addAttribute("writers", writers);
        model.addAttribute("isNew", true);
        return "publication";
    }

    @PostMapping(value = "/publication")
    public String addPublication(Publication publication, BindingResult result) {
        if (result.hasErrors()) {
            return "publication";
        }
        else {
            this.publicationService.addPublication(publication);
            return "redirect:/publications";
        }
    }

    @GetMapping(value = "/publication/{id}")
    public String gotoEditPublication(@PathVariable Integer id, Model model) {
        Publication publication = publicationService.getPublicationById(id);
        Collection<Writer> writers = writerService.getWriters();
        model.addAttribute("publication", publication);
        model.addAttribute("writers", writers);
        model.addAttribute("isNew", false);
        return "publication";
    }

    @PostMapping(value = "/publication/{id}")
    public String editPublication(Publication publication, BindingResult result) {
        if (result.hasErrors()) {
            return "publication";
        }
        else {
            this.publicationService.updatePublication(publication);
            return "redirect:/publications";
        }
    }

    @GetMapping(value = "/publication/{id}/delete")
    public String deletePublicationById(@PathVariable Integer id, Model model) {
        publicationService.deletePublicationById(id);
        return "redirect:/publications";
    }
}
