package bookstore.bookstore.entity.dto;

import lombok.Data;

@Data
public class MedicineDto {

//"name":"psychology of money",
//        "price":"100",
//        "category":"Fiction",
//        "image":"https://images-eu.ssl-images-amazon.com/images/I/71g2ednj0JL._AC_UL200_SR200,200_.jpg"

    private String name;
    private Double price;
    private String category;
    private String image;

    // ... getters, setters, constructors ...
}
