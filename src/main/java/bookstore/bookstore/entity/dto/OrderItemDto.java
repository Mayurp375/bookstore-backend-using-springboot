package bookstore.bookstore.entity.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private MedicineDto medicineDto;
    private Integer quantity;
}
