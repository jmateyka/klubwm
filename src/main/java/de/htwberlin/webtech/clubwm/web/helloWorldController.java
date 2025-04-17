package de.htwberlin.webtech.clubwm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class helloWorldController {


    @GetMapping(path = "/")
    public ModelAndView showHelloWorldPage(){
        return new ModelAndView("helloworld");
    }

}
