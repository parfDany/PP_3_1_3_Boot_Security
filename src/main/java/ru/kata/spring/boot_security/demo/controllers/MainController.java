package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {
    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/user")
    public String pageForAuthenticatedUsers(Principal principal){
        return "user" + principal;
    }

//    @GetMapping("/read_profile")
//    public String pageForReadProfile(){
//        return "read profile page";
//    }
//
//    @GetMapping("/read_for_admins")
//    public String pagerOnlyForAdmins(){
//        return "admins page";
//    }
}
