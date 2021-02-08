package it.academy.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
public class HomeController {

    private static  final Logger log=Logger.getLogger(HomeController.class.getName());

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView home(ModelAndView modelAndView, @RequestParam(name = "param", required = false) String param){
        log.info("Calling home()");
        modelAndView.addObject("paramValue", param);
        modelAndView.addObject("userName", "Guest");
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
