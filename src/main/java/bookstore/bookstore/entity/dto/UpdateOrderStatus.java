package bookstore.bookstore.entity.dto;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UpdateOrderStatus {
    private Long orderId;
    @Enumerated(EnumType.STRING)
    private String status;
    private String message;
    private Long sellerId;
    private Long userId;

}
