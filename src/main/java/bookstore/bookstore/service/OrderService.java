package bookstore.bookstore.service;

import bookstore.bookstore.entity.*;
import bookstore.bookstore.repository.CartRepository;
import bookstore.bookstore.repository.OrderRepository;
import bookstore.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    public Order placeOrder(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        Cart cart = cartRepository.findByUser(user).orElse(null);

        if (user == null || cart == null || cart.getItems().isEmpty()) {
            // Handle error appropriately
            return null;
        }

        Order order = new Order(user);
        for (CartItem item : cart.getItems()) {
            OrderItem orderItem = new OrderItem(item.getBook(), item.getQuantity());
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
