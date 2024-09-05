package bookstore.bookstore.service;

import bookstore.bookstore.entity.Medicine;
import bookstore.bookstore.entity.Order;
import bookstore.bookstore.entity.OrderItem;
import bookstore.bookstore.entity.User;
import bookstore.bookstore.entity.dto.OrderDto;
import bookstore.bookstore.entity.dto.OrderItemDto;
import bookstore.bookstore.entity.response.ResponseDto;
import bookstore.bookstore.entity.role.OrderStatus;
import bookstore.bookstore.repository.CartRepository;
import bookstore.bookstore.repository.OrderRepository;
import bookstore.bookstore.repository.UserRepository;
import bookstore.bookstore.util.JWTToken;
import bookstore.bookstore.util.constant.AppConstant;
import bookstore.bookstore.util.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final JWTToken jwtToken;

    public OrderService(OrderRepository orderRepository,
                        CartRepository cartRepository,
                        UserRepository userRepository,
                        JWTToken jwtToken) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.jwtToken = jwtToken;
    }

    public ApiResponse placeOrder(OrderDto orderDto) {

        Long decodeToken = jwtToken.decodeToken(orderDto.getToken());
        User user = userRepository.findById(decodeToken).orElse(null);

        if (Objects.isNull(user)) {
            try {
                throw new UserPrincipalNotFoundException(HttpStatus.UNAUTHORIZED.getReasonPhrase());
            } catch (UserPrincipalNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderDto.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setMedicine(new Medicine(orderItemDto.getMedicineDto()));
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItems.add(orderItem);
        }
        orderRepository.save(new Order(orderDto, OrderStatus.RECEIVED_BY_SELLER, user, orderItems));
        Order order = orderRepository.findFirstByUser(user);
        if (order != null) {
            return new ApiResponse(HttpStatus.OK.value(), AppConstant.SUCCESS, "Order successfully placed, Order Id ".concat(String.valueOf(order.getId())));
        }
        return new ApiResponse(HttpStatus.NOT_FOUND.value(), AppConstant.FAILURE, "Order not placed, Order Id ");
    }

    public Optional<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findById(userId);
    }

    // ... methods like viewOrders, getOrderDetails etc. ...
}
