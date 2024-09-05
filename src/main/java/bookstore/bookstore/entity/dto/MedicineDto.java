package bookstore.bookstore.entity.dto;

import lombok.Data;

@Data
public class MedicineDto {
    private String name;
    private Double price;
    private String category;
    private String image;

    // ... getters, setters, constructors ...
}
