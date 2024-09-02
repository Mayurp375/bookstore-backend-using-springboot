package bookstore.bookstore.controller;


import bookstore.bookstore.entity.Order;
import bookstore.bookstore.entity.dto.OrderDto;
import bookstore.bookstore.service.OrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("http://localhost:4200/")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //http://localhost:8080/api/orders/place
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderDto orderDto, WebRequest request) {
        orderDto.setAuthToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        return new ResponseEntity<>(orderService.placeOrder(orderDto), HttpStatus.CREATED);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<Optional<Order>> viewOrderHistory(@PathVariable Long userId) {
        return new ResponseEntity<>(orderService.getOrdersByUserId(userId), HttpStatus.OK);
    }
}
