package bookstore.bookstore.entity.dto;

import bookstore.bookstore.entity.Medicine;
import bookstore.bookstore.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private String authToken;
    private Integer amount;
    private List<Long> medicineId;
}
