package hu.progmatic.spring_modul_close.controller;

import hu.progmatic.spring_modul_close.model.User;
import hu.progmatic.spring_modul_close.service.EntityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final EntityService entityService;

    public UserController(EntityService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/user/{id}")
    public String getUserById(Model model, @PathVariable Integer id) {
        User user = entityService.getUserById(id);
        model.addAttribute("orders", entityService.getAllOrderByUser(user));
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("new_user", new User());
        return "user-form";
    }

    @PostMapping("/add")
    public String createUser(@ModelAttribute("user") User user) {
        entityService.saveUser(user);
        return "redirect:/home";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, User user) {
        entityService.deleteOrderByUser(user);
        entityService.deleteUserById(id);
        return "redirect:/home";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", entityService.getUserById(id));
        return "user-update";
    }

    @PostMapping("/update/{id}")
    public String updatePerson(@ModelAttribute("user") User updated, @PathVariable("id") Integer id) {
        entityService.saveUser(updated);
        return "redirect:/users/user/" + id;
    }

    @GetMapping("/most-order")
    public String getMostOrder(Model model) {
        User user = entityService.getMostOrderUser();
        model.addAttribute("user", user);
        model.addAttribute("order", entityService.getAllOrderByUser(user));
        return "user";
    }
}
