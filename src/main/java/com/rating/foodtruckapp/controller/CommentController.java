package com.rating.foodtruckapp.controller;

import com.rating.foodtruckapp.exception.InformationNotFoundException;
import com.rating.foodtruckapp.model.Comment;
import com.rating.foodtruckapp.model.TruckProfile;
import com.rating.foodtruckapp.repository.TruckRepository;
import com.rating.foodtruckapp.service.CommentService;
import com.rating.foodtruckapp.service.TruckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    TruckService truckService;

    @Autowired
    TruckRepository truckRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/foodtrucks/{truckProfileId}/comment")
    public String getComments(Model model, @PathVariable Long truckProfileId) {
        logger.debug("Comment for " + truckProfileId + " ID");
        try {
            TruckProfile truckProfile = truckRepository.findTruckProfilesByTruckid(truckProfileId);
            List<Comment> comments = commentService.getComments(truckProfile);
            model.addAttribute("truckProfileId", truckProfileId);
            model.addAttribute("comment", comments);
            model.addAttribute("truckProfile", truckProfile);
            return "comment";

        } catch (InformationNotFoundException e) {
            String errorMessage = e.getMessage();
            logger.error(errorMessage);
            model.addAttribute("no Comments for trucks", errorMessage);
            return "redirect:/foodtruck-list";
        }

    }

    @GetMapping(value = {"/foodtrucks/{truckProfileId}/comment/add"})
    public String showAddComment(Model model, @PathVariable
                                         long truckProfileId, @ModelAttribute("newComment") Comment newComment) {

            return "comment-add";

    }

    @PostMapping(value = "/foodtrucks/{truckProfileId}/comment/save")
    public String saveComment(Model model, @ModelAttribute("newComment")
    @RequestBody Comment newComment,
     @PathVariable long truckProfileId) {
        try {
            commentService.saveComment(newComment, truckProfileId);
            return "redirect:/foodtrucks/{truckProfileId}/comment";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "comment-add";
        }
    }

}


