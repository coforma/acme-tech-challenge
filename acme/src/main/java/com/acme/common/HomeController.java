package com.acme.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @GetMapping({"/","/index.html"})
    public String index( Model model) {
          return "index";
    }
    @GetMapping({"/login"})
    public String login( Model model) {
          return "login";
    }
}