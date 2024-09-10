package bookstore.bookstore.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private List<OrderItemDto> items;
    private Date date;
    private Double totalAmount;
    private String address;
    private Long user;
}
