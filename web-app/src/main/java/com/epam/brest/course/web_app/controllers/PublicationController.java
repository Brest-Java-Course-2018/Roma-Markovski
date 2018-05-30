package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dto.PublicationDTO;
import com.epam.brest.course.model.Publication;
import com.epam.brest.course.model.Writer;
import com.epam.brest.course.service.PublicationService;
import com.epam.brest.course.service.WriterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

/**
 * Controller class for publications.
 */
@Controller
public class PublicationController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Service.
     */
    @Autowired
    private PublicationService publicationService;

    /**
     * Service.
     */
    @Autowired
    private WriterService writerService;

    /**
     * Displays publications.
     * @return view name
     */
    @GetMapping(value = "/publications")
    public final String getPublications(final Model model) {
        LOGGER.debug("getPublications({})", model);
        Collection<PublicationDTO> publications =
                publicationService.getPublicationDTOs();
        model.addAttribute("publications", publications);
        return "publications";
    }

    /**
     * Displays adding form.
     * @return view name
     */
    @GetMapping(value = "/publication")
    public final String gotoAddPublication(final Model model) {
        LOGGER.debug("gotoAddPublication({})", model);
        Publication publication = new Publication();
        Collection<Writer> writers = writerService.getWriters();
        model.addAttribute("publication", publication);
        model.addAttribute("writers", writers);
        model.addAttribute("isNew", true);
        return "publication";
    }

    /**
     * Adds publication.
     * @return view name
     */
    @PostMapping(value = "/publication")
    public final String addPublication(
            final Publication publication, final BindingResult result) {
        LOGGER.debug("addPublication({}, {})", publication, result);
        if (result.hasErrors()) {
            return "publication";
        } else {
            this.publicationService.addPublication(publication);
            return "redirect:/publications";
        }
    }

    /**
     * Displays editing form.
     * @return view name
     */
    @GetMapping(value = "/publication/{id}")
    public final String gotoEditPublication(
            @PathVariable final Integer id, final Model model) {
        LOGGER.debug("gotoEditPublication({}, {})", id, model);
        Publication publication = publicationService.getPublicationById(id);
        Collection<Writer> writers = writerService.getWriters();
        model.addAttribute("publication", publication);
        model.addAttribute("writers", writers);
        model.addAttribute("isNew", false);
        return "publication";
    }

    /**
     * Edits publication.
     * @return view name
     */
    @PostMapping(value = "/publication/{id}")
    public final String editPublication(
            final Publication publication, final BindingResult result) {
        LOGGER.debug("editPublication({}, {})", publication, result);
        if (result.hasErrors()) {
            return "publication";
        } else {
            this.publicationService.updatePublication(publication);
            return "redirect:/publications";
        }
    }

    /**
     * Displays deleting modal window.
     * @return view name
     */
    @GetMapping(value = "/publication/{id}/delete")
    public final String deletePublicationById(
            @PathVariable final Integer id, final Model model) {
        LOGGER.debug("deletePublicationById({}, {})", id, model);
        publicationService.deletePublicationById(id);
        return "redirect:/publications";
    }
}
