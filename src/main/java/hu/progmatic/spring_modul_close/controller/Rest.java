package hu.progmatic.spring_modul_close.controller;

import hu.progmatic.spring_modul_close.model.Order;
import hu.progmatic.spring_modul_close.model.User;
import hu.progmatic.spring_modul_close.service.EntityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class Rest {
    private final EntityService entityService;

    public Rest(EntityService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        return entityService.getAllUsers();
    }

    @GetMapping("/orders")
    public List<Order> getAllOrder() {
        return entityService.getAllOrders();
    }

    @GetMapping("/user/{id}/orders")
    public List<Order> getAllOrdersByUser(@PathVariable("id") Integer id) {
        return entityService.getAllOrderByUser(entityService.getUserById(id));
    }
}
