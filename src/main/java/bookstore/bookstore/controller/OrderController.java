package bookstore.bookstore.controller;


import bookstore.bookstore.entity.Order;
import bookstore.bookstore.entity.dto.OrderItemDto;
import bookstore.bookstore.entity.dto.UpdateOrderStatus;
import bookstore.bookstore.service.interfaces.OrderService;
import bookstore.bookstore.util.JWTToken;
import bookstore.bookstore.util.constant.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("http://localhost:4200/")
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final JWTToken jwtToken;

    public OrderController(OrderService orderService, JWTToken jwtToken) {
        this.orderService = orderService;
        this.jwtToken = jwtToken;
    }

    //http://localhost:8080/api/orders/place
    @PostMapping("/place")
    public ResponseEntity<?> createOrder(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody OrderItemDto orderItemsDTO) {
        log.info("request : {}",orderItemsDTO);
        Map<String,Object> map = new HashMap<>();
        map.put(AppConstant.CODE,HttpStatus.OK.value());
        map.put(AppConstant.STATUS,AppConstant.SUCCESS);
        try {
            Long userId = jwtToken.decodeToken(token);
            orderService.createOrder(userId, orderItemsDTO);
            map.put(AppConstant.DATA,"Order created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(map);
        } catch (Exception e) {
            map.put(AppConstant.DATA,"Error creating order: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        try {
            Order order = orderService.getOrder(orderId);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/list/{orderId}")
    public ResponseEntity<Map> all(@PathVariable Long orderId) {
        try {
            List<Order> order = orderService.getOrderDetails(orderId);
            Map map = new HashMap();
            map.put("order",order);
            log.info("orders data   list :{}",map);
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
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
        orderStatus.setSellerId(jwtToken.getUser(token));
        return new ResponseEntity<>(orderService.updateOrderStatus(orderStatus), HttpStatus.OK);
    }
}
