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
    public String login() { return "/user/login"; }

    @GetMapping("/register")
    public String register() { return "/user/register"; }

    @GetMapping("/register-complete")
    public String registerComplete() { return "/user/register-complete"; }

    @GetMapping("/logout")
    public String logout() { return "/user/logout"; }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }

}
