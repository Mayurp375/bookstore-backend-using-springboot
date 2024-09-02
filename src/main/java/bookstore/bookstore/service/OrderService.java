package bookstore.bookstore.service;

import bookstore.bookstore.entity.*;
import bookstore.bookstore.entity.dto.OrderDto;
import bookstore.bookstore.entity.role.OrderStatus;
import bookstore.bookstore.repository.CartRepository;
import bookstore.bookstore.repository.OrderRepository;
import bookstore.bookstore.repository.UserRepository;
import bookstore.bookstore.util.JWTToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final MedicineService medicineService;
    private final JWTToken jwtToken;

    public OrderService(OrderRepository orderRepository,
                        CartRepository cartRepository,
                        UserRepository userRepository,
                        JWTToken jwtToken,
                        MedicineService medicineService) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.jwtToken = jwtToken;
        this.medicineService = medicineService;

    }

    public Order placeOrder(OrderDto orderDto) {
        Long decodeToken = jwtToken.decodeToken(orderDto.getAuthToken());
        List<Medicine> medicines = new ArrayList<>();
        for (Long id : orderDto.getMedicineId()) {
            Medicine medicineById = medicineService.getMedicineById(id);
            medicines.add(medicineById);
        }

        User user = userRepository.findById(decodeToken).orElse(null);
        Cart cart = cartRepository.findByUser(user).orElse(null);

        if (user == null || cart == null || cart.getItems().isEmpty()) {
            // Handle error appropriately
            return null;
        }

        Order order = new Order(user);
        order.setOrderStatus(OrderStatus.RECEIVED);
        order.setOrderDate(LocalDateTime.now());
        for (CartItem item : cart.getItems()) {
            OrderItem orderItem = new OrderItem(item.getMedicine(), item.getQuantity());
            order.getItems().add(orderItem);
        }

        cart.getItems().clear();
        cartRepository.save(cart);

        return orderRepository.save(order);
    }

    public Optional<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findById(userId);
    }

    // ... methods like viewOrders, getOrderDetails etc. ...
}
