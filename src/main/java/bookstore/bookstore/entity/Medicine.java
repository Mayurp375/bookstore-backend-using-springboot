package bookstore.bookstore.entity;

import bookstore.bookstore.entity.dto.MedicineDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "medicine")
@Data
@NoArgsConstructor
public class Medicine {

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

    public Medicine(MedicineDto medicineDto) {
        this.name = medicineDto.getName();
        this.price = medicineDto.getPrice();
        this.category = medicineDto.getCategory();
        this.image = medicineDto.getImage();
    }
    // ... getters, setters, constructors ...
}
