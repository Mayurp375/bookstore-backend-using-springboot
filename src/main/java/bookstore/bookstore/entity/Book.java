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

//    category:"Non-Fiction"
//    description:"200"
//    id:1
//    image:"https://m.media-amazon.com/images/I/814L+vq01mL._SY466_.jpg"
//    name:"Ikigai Hardcover"

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private Double price;

    private String image;

    // ... getters, setters, constructors ...
}
