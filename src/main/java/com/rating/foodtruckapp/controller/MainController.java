package com.rating.foodtruckapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    /**
     * This returns the home page or index
     * @return
     */
    //http://localhost:8084/
    @GetMapping("/")
    public String root() {
        return "index";
    }

    /**
     * The model grabs the logged-in user and directs them to the index page
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String login(Model model) {
        System.out.println("logging in ====================");
        return "login";
    }


    /**
     * this model grabs the logged-in user and directs them to the user index page
     * @param model
     * @return
     */
    @GetMapping("/user")
    public String userIndex(Model model) {
        System.out.println("user index page =================>");
        return "user/index";
    }
}