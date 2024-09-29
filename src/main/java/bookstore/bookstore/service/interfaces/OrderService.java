package bookstore.bookstore.service.interfaces;

import bookstore.bookstore.entity.Order;
import bookstore.bookstore.entity.dto.OrderItemDto;
import bookstore.bookstore.entity.dto.UpdateOrderStatus;
import bookstore.bookstore.util.response.ApiResponse;

import java.util.List;
import java.util.Map;

public interface OrderService {
    void createOrder(Long userId, OrderItemDto orderItemsDTO);
    Order getOrder(Long orderId);
    List<Order> getOrderDetails(Long orderId);
    List<Order> getOrdersByUserId(String request);
    Map sellerOrder(String request);
    ApiResponse updateOrderStatus(UpdateOrderStatus orderStatus);
}
