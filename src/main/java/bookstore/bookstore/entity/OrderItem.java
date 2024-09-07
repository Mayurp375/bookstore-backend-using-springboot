package bookstore.bookstore.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    private Integer quantity;
    private BigDecimal price;

// Add other order item attributes (e.g., price, discount)
}