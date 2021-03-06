package edu.hawaii.its.api.controller;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private static final Log logger = LogFactory.getLog(HomeController.class);

    //todo Covered by Integration Tests
    // Mapping to home.
    @RequestMapping(value = {"/", "/home"}, method = {RequestMethod.GET})
    public String home(Map<String, Object> model, Locale locale) {
        logger.info("User at home. The client locale is " + locale);
        return "home";
    }

    //todo Covered by Integration Tests
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Locale locale, Model model) {
        logger.info("User has logged in.");
        return "redirect:home";
    }
}
