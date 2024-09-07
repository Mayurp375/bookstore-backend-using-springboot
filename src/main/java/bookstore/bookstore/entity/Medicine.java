package bookstore.bookstore.entity;

import bookstore.bookstore.entity.dto.MedicineDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "medicine")
@NoArgsConstructor
@Data
public class Medicine {
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
