package bookstore.bookstore.controller;

import bookstore.bookstore.entity.role.OrderStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnumController {

    @GetMapping("/order-statuses")
    public OrderStatus[] getOrderStatuses() {
        return OrderStatus.values();
    }
}