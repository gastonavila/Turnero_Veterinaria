package com.egg.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PrincipalController {

    @GetMapping
    public ModelAndView inicio() {
        return new ModelAndView("index");
    }

}
