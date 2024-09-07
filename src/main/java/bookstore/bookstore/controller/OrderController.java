package bookstore.bookstore.controller;


import bookstore.bookstore.entity.Order;
import bookstore.bookstore.entity.dto.OrderDto;
import bookstore.bookstore.entity.dto.UpdateOrderStatus;
import bookstore.bookstore.service.OrderService;
import bookstore.bookstore.util.JWTToken;
import bookstore.bookstore.util.response.ApiResponse;
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
    private final JWTToken jwtToken;

    public OrderController(OrderService orderService, JWTToken jwtToken) {
        this.orderService = orderService;
        this.jwtToken = jwtToken;
    }

    //http://localhost:8080/api/orders/place
    @PostMapping("/place")
    public ResponseEntity<ApiResponse> placeOrder(@RequestBody OrderDto orderDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        return ResponseEntity.ok(orderService.placeOrder(orderDto,token));
    }

    @GetMapping("/history")
    public ResponseEntity<?> viewOrderHistory(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return new ResponseEntity<>(orderService.getOrdersByUserId(token), HttpStatus.OK);
    }

    @GetMapping("/allOrders")
    public ResponseEntity<?> allOrders(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return new ResponseEntity<>(orderService.sellerOrder(token), HttpStatus.OK);
    }

    @GetMapping("/updateOrderStatus")
    public ResponseEntity<?> updateOrderStatus(@RequestBody UpdateOrderStatus orderStatus,@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        orderStatus.setUserId(jwtToken.getUser(token));
        return new ResponseEntity<>(orderService.updateOrderStatus(orderStatus), HttpStatus.OK);
    }
}
