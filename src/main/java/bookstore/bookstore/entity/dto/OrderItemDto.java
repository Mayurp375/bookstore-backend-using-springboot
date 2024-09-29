package bookstore.bookstore.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderItemDto {
    private String address;
    private Integer totalAmount;
    private Integer contact;
    private List<ItemsDto> itemsDtoList;

    @Data
    public static class ItemsDto {
        private Long medicineId;
        private Integer quantity;
    }
}
