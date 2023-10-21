package hu.progmatic.spring_modul_close.controller;

import hu.progmatic.spring_modul_close.model.Order;
import hu.progmatic.spring_modul_close.service.EntityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final EntityService entityService;

    public OrderController(EntityService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/new/{user-id}")
    public String createOrder(Model model, @PathVariable("user-id") Integer userId) {
        model.addAttribute("userId", userId);
        model.addAttribute("order", new Order());
        return "order-form";
    }

    @PostMapping("/add/{user-id}")
    public String createOrder(@ModelAttribute("order") Order order, @PathVariable("user-id") Integer userId) {
        order.setUser(entityService.getUserById(userId));
        entityService.saveOrder(order);
        return "redirect:/home";
    }
}
