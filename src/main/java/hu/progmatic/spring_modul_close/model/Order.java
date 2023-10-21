package hu.progmatic.spring_modul_close.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String placeOfOrder;
    private Integer price;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Order(Integer id, String placeOfOrder, Integer price, User user) {
        this.id = id;
        this.placeOfOrder = placeOfOrder;
        this.price = price;
        this.user = user;
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaceOfOrder() {
        return placeOfOrder;
    }

    public void setPlaceOfOrder(String placeOfOrder) {
        this.placeOfOrder = placeOfOrder;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", placeOfOrder='" + placeOfOrder + '\'' +
                ", price=" + price +
                ", user=" + user +
                '}';
    }

}
