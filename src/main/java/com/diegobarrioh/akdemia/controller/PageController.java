package com.diegobarrioh.akdemia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/logout")
    public String logout() { return "logout"; }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }

}
