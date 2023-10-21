package hu.progmatic.spring_modul_close.controller;

import hu.progmatic.spring_modul_close.service.EntityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    private final EntityService entityService;

    public PageController(EntityService entityService) {
        this.entityService = entityService;
    }

    @GetMapping({"", "/", "/home"})
    public String getHome(Model model) {
        model.addAttribute("user",entityService.getAllUsers());
        return "home";
    }
}
