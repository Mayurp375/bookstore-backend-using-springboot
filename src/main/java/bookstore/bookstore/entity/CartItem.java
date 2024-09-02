package bookstore.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;
    
    private Integer quantity;

    public CartItem(Cart cart, Medicine medicine) {
        this.cart = cart;
        this.medicine = medicine;
    }


    public CartItem(Long id, Medicine medicine) {
        this.id = id;
        this.medicine = medicine;
    }

    public CartItem(Medicine book, int quantity) {
        this.medicine=book;
        this.quantity=quantity;
    }
}
