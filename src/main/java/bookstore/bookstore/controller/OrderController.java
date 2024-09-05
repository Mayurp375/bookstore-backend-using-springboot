package bookstore.bookstore.controller;


import bookstore.bookstore.entity.Order;
import bookstore.bookstore.entity.dto.OrderDto;
import bookstore.bookstore.service.OrderService;
import bookstore.bookstore.util.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<ApiResponse> placeOrder(@RequestBody OrderDto orderDto, WebRequest request) {
        orderDto.setToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        return ResponseEntity.ok(orderService.placeOrder(orderDto));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<Optional<Order>> viewOrderHistory(@PathVariable Long userId) {
        return new ResponseEntity<>(orderService.getOrdersByUserId(userId), HttpStatus.OK);
    }
}
