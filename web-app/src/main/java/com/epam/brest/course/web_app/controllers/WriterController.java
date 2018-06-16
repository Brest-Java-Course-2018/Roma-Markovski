package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.client.ServerDataAccessException;
import com.epam.brest.course.dto.WriterDTO;
import com.epam.brest.course.model.Writer;
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
 * Controller class for writers.
 */
@Controller
public class WriterController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Service.
     */
    @Autowired
    private WriterService writerService;

    /**
     * Displays writers.
     * @return view name
     */
    @GetMapping(value = "/writers")
    public final String getWriterDTOs(final Model model) {
        LOGGER.debug("getWriterDTOs({})", model);
        Collection<WriterDTO> writers = writerService.getWriterDTOs();
        model.addAttribute("writers", writers);
        LOGGER.debug("getWriterDTOs returned: 'writers'");
        return "writers";
    }

    /**
     * Displays adding form.
     * @return view name
     */
    @GetMapping(value = "/writer")
    public final String gotoAddWriter(final Model model) {
        LOGGER.debug("gotoAddWriter({})", model);
        Writer writer = new Writer();
        model.addAttribute("writer", writer);
        model.addAttribute("isNew", true);
        LOGGER.debug("gotoAddWriter returned: 'writer'");
        return "writer";
    }

    /**
     * Adds writer.
     * @return view name
     */
    @PostMapping(value = "/writer")
    public final String addWriter(
            final @Valid Writer writer, final BindingResult result) {
        LOGGER.debug("addWriter({}, {})", writer, result);
        if (result.hasErrors()) {
            LOGGER.debug("addWriter had errors and returned: 'writer'");
            return "writer";
        } else {
            this.writerService.addWriter(writer);
            LOGGER.debug("addWriter returned: 'redirect:/writers'");
            return "redirect:/writers";
        }
    }

    /**
     * Displays editing form.
     * @return view name
     */
    @GetMapping(value = "/writer/{id}")
    public final String gotoEditWriter(
            @PathVariable final Integer id, final Model model) {
        LOGGER.debug("gotoEditWriter({}, {})", id, model);
        Writer writer = writerService.getWriterById(id);
        model.addAttribute("writer", writer);
        model.addAttribute("isNew", false);
        LOGGER.debug("gotoEditWriter returned: 'writer'");
        return "writer";
    }

    /**
     * Edits writer.
     * @return view name
     */
    @PostMapping(value = "/writer/{id}")
    public final String editWriter(
            final @Valid Writer writer, final BindingResult result) {
        LOGGER.debug("gotoEditWriter({}, {})", writer, result);
        if (result.hasErrors()) {
            LOGGER.debug("editWriter had errors and returned: 'writer'");
            return "writer";
        } else {
            this.writerService.updateWriter(writer);
            LOGGER.debug("editWriter returned: 'redirect:/writers'");
            return "redirect:/writers";
        }
    }

    /**
     * Displays deleting modal window.
     * @return view name
     */
    @GetMapping(value = "/writer/{id}/delete")
    public final String deleteWriterById(
            @PathVariable final Integer id, final Model model) {
        LOGGER.debug("deleteWriterById({}, {})", id, model);
        writerService.deleteWriterById(id);
        LOGGER.debug("deleteWriterById returned: 'redirect:/writers'");
        return "redirect:/writers";
    }

    @GetMapping(value="/errorExample")
    public final String throwError(
            final Model model) {
        LOGGER.debug("throwError({}, {})", model);
        throw new ServerDataAccessException("Example error.");
    }
}
