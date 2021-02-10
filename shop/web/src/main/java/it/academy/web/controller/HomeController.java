package it.academy.web.controller;


import it.academy.service.PromoService;
import it.academy.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Controller
public class HomeController {

    private static  final Logger log=Logger.getLogger(HomeController.class.getName());
    @Autowired
    SearchService searchService;

    @Autowired
    PromoService promoService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(@RequestParam(name = "searchParam", required = false) String param,
                             Model model){
        log.info("Calling home(): searchParam: "+ param);

        if (param!=null&&!"".equals(param.trim())){
            model.addAttribute("searchResult", searchService.searchProducts(param));
            return "search-result";
        }

        model.addAttribute("promoList", promoService.findAllPromo());

        return "index";
    }
}
