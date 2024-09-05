package bookstore.bookstore.entity;

import bookstore.bookstore.entity.dto.OrderDto;
import bookstore.bookstore.entity.role.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    private LocalDateTime orderDate;
    private Double totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order() {
    }

    public Order(OrderDto orderDto, OrderStatus orderStatus, User user, ArrayList<OrderItem> orderItems) {
        this.user = user;
        this.items =  orderItems;
        this.orderDate = orderDto.getOrderDate();
        this.totalAmount = orderDto.getTotalAmount();
        this.orderStatus = orderStatus;
    }

}
