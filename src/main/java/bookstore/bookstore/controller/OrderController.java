package bookstore.bookstore.controller;

import bookstore.bookstore.entity.Order;
import bookstore.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestParam Long userId) {
        return new ResponseEntity<>(orderService.placeOrder(userId), HttpStatus.CREATED);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<Optional<Order>> viewOrderHistory(@PathVariable Long userId) {
        return new ResponseEntity<>(orderService.getOrdersByUserId(userId), HttpStatus.OK);
    }
}
