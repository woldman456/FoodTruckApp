package com.rating.foodtruckapp.controller;

import java.util.List;
import java.util.Optional;
import com.rating.foodtruckapp.exception.InformationNotFoundException;
import com.rating.foodtruckapp.model.TruckProfile;
import com.rating.foodtruckapp.service.TruckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Controller
public class TruckController {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    TruckService truckService;

    /**
     * this pull the model which is keyed to the truckProfile and returns a list of trucks
     * @param model
     * @return
     * http://localhost:8084/foodtrucks
     */
    @GetMapping(value = "/foodtrucks")
    public String getAllTruckProfiles(Model model) {

        List<TruckProfile> truckProfiles = truckService.findAll();
        model.addAttribute("truckProfiles", truckProfiles);
        return "foodtruck-list";
    }

    /**
     * this method renders the add food truck page
     * @param newTruck
     * @return
     * http://localhost:8084/foodtrucks/add
     */
    @GetMapping(value = {"foodtrucks/add"})
    public String showAddNewTruckForm(@ModelAttribute("newTruck") TruckProfile newTruck)
    {
        return "foodtruck-add";
    }

    /**
     * This method creates a new truckProfile
     * @param model
     * @param newTruck
     * @return
     * http://localhost:8084/foodtrucks/save
     */
    @PostMapping(value = "/foodtrucks/save")
    public String saveTruck(Model model, @ModelAttribute("newTruck") TruckProfile newTruck) {
        try {
            truckService.saveNewTruck(newTruck);
            return "redirect:/foodtrucks";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "foodtruck-add";
        }
    }

    /**
     * This method is used to show the updated truck profile form and will check if the truck exists.
     * @param model
     * @param truckProfileId
     * @return
     */ //http://localhost:8084/foodtrucks/{truckProfileId}/edit

    @GetMapping(value = {"/foodtrucks/{truckProfileId}/edit"})
    public String showUpdateFoodTruckProfileForm(
            Model model,
            @PathVariable long truckProfileId) {
        logger.debug("Calling showEditFoodTruckProfile");
        Optional<TruckProfile> truckProfile = null;
        try {
            truckProfile = truckService.findById(truckProfileId);
            model.addAttribute("oldTruckProfile", truckProfile);
            return "foodtruck-edit";
        } catch (InformationNotFoundException e) {
            String errorMessage = e.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "foodtruck-edit";
        }
    }

    /**
     * This method will save the truck profile form, and will update if the form has been properly filled out.
     * @param model
     * @param truckProfileId
     * @param truckUpdateProfile
     * @return
     */ //http://localhost:8084/foodtrucks/{truckProfileId}/edit

    @PostMapping(value = {"/foodtrucks/{truckProfileId}/edit"})
    public String saveUpdateFoodTruckProfileForm(Model model,
                                                 @PathVariable long truckProfileId,
                                                 @ModelAttribute("truckUpdateProfile") TruckProfile truckUpdateProfile) {
        try {
            truckService.updateTruckProfile(truckProfileId, truckUpdateProfile);
            return "redirect:/foodtrucks";
        } catch (InformationNotFoundException e) {
            String errorMessage = e.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);

            return "foodtruck-list";
        }
    }

    /**
     * This method is used to allow for an admin user to delete an instance of a truck and it's details
     * @param model
     * @param truckProfileId
     * @param truckProfile
     * @return
     */
    @RequestMapping(value = "/foodtrucks/{truckProfileId}", method = {RequestMethod.GET,RequestMethod.DELETE})
    public String deleteTruck(Model model, @PathVariable long truckProfileId,
                              @ModelAttribute("targetTruckid") TruckProfile truckProfile) {
        try {
            truckService.deleteTruck(truckProfileId, truckProfile);
            return "redirect:/foodtrucks";
        } catch (InformationNotFoundException e) {
            String errorMessage = e.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "foodtruck-list";
        }
    }



}
