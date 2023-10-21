package hu.progmatic.spring_modul_close.repository;

import hu.progmatic.spring_modul_close.model.Order;
import hu.progmatic.spring_modul_close.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> getOrdersByUser(User user);
    void deleteAllByUser(User user);
}
