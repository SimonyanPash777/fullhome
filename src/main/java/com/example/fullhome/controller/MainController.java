package com.example.fullhome.controller;

import com.example.fullhome.entity.Role;
import com.example.fullhome.entity.User;
import com.example.fullhome.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String MainPage() {
        return "index";
    }
    @GetMapping("/about")
    public String AboutPage() {
        return "about";
    }
    @GetMapping("/contact")
    public String ContactPage() {
        return "contact";
    }
    @GetMapping("/user/services")
    public String ServicesPage() {
        return "services";
    }
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @GetMapping("/register")
    public String registerController(){
        return "register";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getRole() == Role.ADMIN) {
                return "redirect:/admin";
            }else if(user.getRole() == Role.USER){
                return "redirect:/user";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/loginPage")
    public String loginPage(@RequestParam(value = "error", required = false) String error, ModelMap modelMap) {
        if(error != null && error.equals("true")){
            modelMap.addAttribute("error", "true");
        }
        return "loginPage";
    }
}
