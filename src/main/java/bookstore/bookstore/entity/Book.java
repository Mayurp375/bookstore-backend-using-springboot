package bookstore.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

//"name":"psychology of money",
//        "price":"100",
//        "category":"Fiction",
//        "image":"https://images-eu.ssl-images-amazon.com/images/I/71g2ednj0JL._AC_UL200_SR200,200_.jpg"

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String category;
    private String image;

    // ... getters, setters, constructors ...
}
