package com.example.AppleStore_Project_JavaSpringBoot_CoLy.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()
                && !"anonymousUser".equals(authentication.getName())) {
            model.addAttribute("username", authentication.getName());
            System.out.println(">> User logged in: " + authentication.getName()); // kiểm tra
        } else {
            model.addAttribute("username", null);
            System.out.println(">> No user logged in.");
        }
        return "Home";
    }


    @GetMapping("/macbook-pro")
    public String macbookPro(Model model){
        return "Home-MacBookPro";
    }

    @GetMapping("iphone17-pro")
    public String iphone17Pro(Model model){
        return "Home-Iphone17Pro";
    }

    @GetMapping("/watch-ultra")
    public String watchUltra(Model model){
        return "Home-WatchUltra";
    }




}
