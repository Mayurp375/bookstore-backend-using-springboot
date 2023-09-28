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
    @JoinColumn(name = "book_id")
    private Book book;
    
    private Integer quantity;

    public CartItem(Cart cart, Book book) {
        this.cart = cart;
        this.book = book;
    }


    public CartItem(Long id, Book book) {
        this.id = id;
        this.book = book;
    }

    public CartItem(Book book, int quantity) {
        this.book=book;
        this.quantity=quantity;
    }
}
