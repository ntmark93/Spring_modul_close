package hu.progmatic.spring_modul_close.service;

import hu.progmatic.spring_modul_close.model.Order;
import hu.progmatic.spring_modul_close.model.User;
import hu.progmatic.spring_modul_close.repository.OrderRepository;
import hu.progmatic.spring_modul_close.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EntityService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public EntityService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<Order> getAllOrderByUser(User user) {
        return orderRepository.getOrdersByUser(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public void deleteOrderByUser(User user) {
        orderRepository.deleteAllByUser(user);
    }

    @Transactional
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    public User getMostOrderUser() {
        Map<User, Integer> userOrder = fillUserMap();
        return getMostOrderUserMap(userOrder);
    }

    public User getMostOrderUserMap(Map<User, Integer> userOrder) {
        int total = 0;
        for (var actual : userOrder.entrySet()) {
            if (actual.getValue() > total) {
                total = actual.getValue();
            }
        }
        for (var actual : userOrder.entrySet()) {
            if (actual.getValue() == total) {
                return actual.getKey();
            }
        }
        return null;
    }

    private Map<User, Integer> fillUserMap() {
        Map<User, Integer> userOrder = new HashMap<>();
        addUsersToUserMap(userOrder);
        addOrderToUserMap(userOrder);
        return userOrder;
    }

    private void addUsersToUserMap(Map<User, Integer> userOrder) {
        for (User actual : getAllUsers()) {
            userOrder.put(actual, 0);
        }
    }

    private void addOrderToUserMap(Map<User, Integer> userOrder) {
        for (Order actual : getAllOrders()) {
            User actualUser = actual.getUser();
            userOrder.put(actualUser, userOrder.get(actualUser) + actual.getPrice());
        }
    }
}
