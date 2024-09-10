package bookstore.bookstore.entity.dto;

import lombok.Data;

@Data
public class MedicineDto {

    private String name;
    private Long id;
    private Double price;
    private String category;
    private String image;

    // ... getters, setters, constructors ...
}
