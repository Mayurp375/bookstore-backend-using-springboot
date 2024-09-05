package bookstore.bookstore.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto {
    private List<OrderItemDto> items;
    private LocalDateTime orderDate = LocalDateTime.now();
    private Double totalAmount;
    private String address;
    private String token;
}
