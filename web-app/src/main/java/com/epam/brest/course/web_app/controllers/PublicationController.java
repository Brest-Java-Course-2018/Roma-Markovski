package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dto.PublicationDTO;
import com.epam.brest.course.model.DateInterval;
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

import javax.validation.Valid;
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
    public final String getPublicationDTOs(final Model model) {
        LOGGER.debug("getPublicationDTOs({})", model);
        Collection<PublicationDTO> publications =
                publicationService.getPublicationDTOs();
        DateInterval dateInterval = new DateInterval();
        model.addAttribute("publications", publications);
        model.addAttribute("dateInterval", dateInterval);
        model.addAttribute("isCollapsed", true);
        LOGGER.debug("getPublicationDTOs returned: 'publications'");
        return "publications";
    }

    /**
     * Displays publications filtered by date.
     * @return view name
     */
    @PostMapping(value = "/publications")
    public final String getPublicationDTOsByDate(
            final Model model,
            @Valid final DateInterval dateInterval,
            final BindingResult result) {
        LOGGER.debug("getPublicationDTOsByDate({}, {}, {})",
                model, dateInterval, result);
        if (result.hasErrors()) {
            Collection<PublicationDTO> publications =
                    publicationService.getPublicationDTOs();
            model.addAttribute("publications", publications);
            LOGGER.debug("getPublicationDTOsByDate had errors"
                    + " and returned: 'publications'");
        } else {
            Collection<PublicationDTO> filteredPublications =
                    publicationService.getPublicationDTOsByDate(
                            dateInterval);
            model.addAttribute("publications", filteredPublications);
            model.addAttribute("dateInterval", dateInterval);
            model.addAttribute("isCollapsed", false);
            LOGGER.debug("getPublicationDTOsByDate returned: 'publications'");
        }
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
        LOGGER.debug("gotoAddPublication returned: 'publication'");
        return "publication";
    }

    /**
     * Adds publication.
     * @return view name
     */
    @PostMapping(value = "/publication")
    public final String addPublication(
            final Model model,
            final @Valid Publication publication,
            final BindingResult result) {
        LOGGER.debug("addPublication({}, {})", publication, result);
        if (result.hasErrors()) {
            LOGGER.debug(
                    "addPublication had errors and returned: 'publication'");
            Collection<Writer> writers = writerService.getWriters();
            model.addAttribute("writers", writers);
            model.addAttribute("isNew", true);
            return "publication";
        } else {
            this.publicationService.addPublication(publication);
            LOGGER.debug(
                    "addPublication had errors and returned:"
                            + " 'redirect:/publications'");
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
        LOGGER.debug("gotoEditPublication returned: 'publication'");
        return "publication";
    }

    /**
     * Edits publication.
     * @return view name
     */
    @PostMapping(value = "/publication/{id}")
    public final String editPublication(
            final Model model,
            final @Valid Publication publication,
            final BindingResult result) {
        LOGGER.debug("editPublication({}, {})", publication, result);
        if (result.hasErrors()) {
            LOGGER.debug(
                    "editPublication had errors and returned: 'publication'");
            Collection<Writer> writers = writerService.getWriters();
            model.addAttribute("writers", writers);
            model.addAttribute("isNew", false);
            return "publication";
        } else {
            this.publicationService.updatePublication(publication);
            LOGGER.debug("editPublication returned: 'redirect:/publications'");
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
        LOGGER.debug(
                "deletePublicationById returned: 'redirect:/publications'");
        return "redirect:/publications";
    }
}
