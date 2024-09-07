package bookstore.bookstore.service;

import bookstore.bookstore.entity.Medicine;
import bookstore.bookstore.entity.Order;
import bookstore.bookstore.entity.OrderItem;
import bookstore.bookstore.entity.User;
import bookstore.bookstore.entity.dto.OrderDto;
import bookstore.bookstore.entity.dto.OrderItemDto;
import bookstore.bookstore.entity.dto.UpdateOrderStatus;
import bookstore.bookstore.entity.role.OrderStatus;
import bookstore.bookstore.exceptions.UserNotFound;
import bookstore.bookstore.repository.MedicineRepository;
import bookstore.bookstore.repository.OrderRepository;
import bookstore.bookstore.repository.UserRepository;
import bookstore.bookstore.util.JWTToken;
import bookstore.bookstore.util.constant.AppConstant;
import bookstore.bookstore.util.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;
    private final JWTToken jwtToken;

    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository,
                        JWTToken jwtToken, MedicineRepository medicineRepository) {
        this.orderRepository = orderRepository;

        this.userRepository = userRepository;
        this.jwtToken = jwtToken;
        this.medicineRepository = medicineRepository;
    }

//    @Transactional
//    public ApiResponse placeOrder(OrderDto orderDto, String token) {
//        Long userId = jwtToken.decodeToken(token);
//        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), "User not found"));
//
//        ArrayList<OrderItem> orderItems = new ArrayList<>();
//        for (OrderItemDto orderItemDto : orderDto.getItems()) {
//            OrderItem orderItem = new OrderItem();
//            Medicine fetchedMedicine = medicineRepository.findById(orderItemDto.getMedicineDto().getId()).orElse(new Medicine());
//            orderItem.setMedicine(fetchedMedicine);
//            orderItem.setQuantity(orderItemDto.getQuantity());
//            orderItems.add(orderItem);
//        }
//        Order order1 = new Order();
//        order1.setOrderStatus(OrderStatus.RECEIVED_BY_SELLER);
//        order1.setUser(user);
//        order1.setTotalAmount(BigDecimal.valueOf(orderDto.getTotalAmount()));
//        order1.setOrderItems(orderItems);
//        order1.setOrderDate(orderDto.getDate());
//
//        orderRepository.save(order1);
//        Order order = orderRepository.findFirstByUser(user);
//        if (order != null) {
//            return new ApiResponse(HttpStatus.OK.value(), AppConstant.SUCCESS, "Order successfully placed, Order Id ".concat(String.valueOf(order.getId())));
//        }
//        return new ApiResponse(HttpStatus.NOT_FOUND.value(), AppConstant.FAILURE, "Order not placed, Order Id ");
//    }

    public ApiResponse placeOrder(OrderDto orderDto, String token) {
        Long userId = jwtToken.decodeToken(token);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), "User not found"));

        Order order1 = new Order();
        order1.setUser(user);
        order1.setOrderStatus(OrderStatus.RECEIVED_BY_SELLER);
        order1.setTotalAmount(BigDecimal.valueOf(orderDto.getTotalAmount()));
        order1.setOrderDate(orderDto.getDate());

        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDto orderItemDto : orderDto.getItems()) {
            OrderItem orderItem = new OrderItem();
            Medicine fetchedMedicine = medicineRepository.findById(orderItemDto.getMedicineDto().getId()).orElse(new Medicine());
            orderItem.setMedicine(fetchedMedicine);
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItem.setOrder(order1); // Set the order field in the OrderItem object
            orderItems.add(orderItem);
        }
        order1.setOrderItems(orderItems);

        // Save the Order entity after setting the OrderItem entities
        orderRepository.save(order1);

        Order order = orderRepository.findFirstByUser(user);
        if (order != null) {
            return new ApiResponse(HttpStatus.OK.value(), AppConstant.SUCCESS, "Order successfully placed, Order Id ".concat(String.valueOf(order.getId())));
        }
        return new ApiResponse(HttpStatus.NOT_FOUND.value(), AppConstant.FAILURE, "Order not placed, Order Id ");
    }

    public List<Order> getOrdersByUserId(String request) {
        Long decodeToken = jwtToken.getUser(request);
        User user = userRepository.findById(decodeToken).orElse(null);
        if (Objects.isNull(user)) {
            throw new UserNotFound(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), "User not found");
        }
        return orderRepository.findAllByUser(user);
    }

    public Map sellerOrder(String request) {
        Long userId = jwtToken.getUser(request);
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFound(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), "User not found");
        }
        List<Order> orderList = orderRepository.findByUser(user);

        if (!orderList.isEmpty()) {
            return ApiResponse.responseFormatter(new ApiResponse(HttpStatus.OK.value(), AppConstant.SUCCESS, orderList));
        }
        return ApiResponse.responseFormatter(new ApiResponse(HttpStatus.NOT_FOUND.value(), AppConstant.FAILURE, "No order found"));
    }

    public ApiResponse updateOrderStatus(UpdateOrderStatus orderStatus) {
        User user = userRepository.findById(orderStatus.getUserId()).orElse(null);
        if (user == null) {
            throw new UserNotFound(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), "User not found");
        }
        orderRepository.updateOrderDetails(orderStatus.getMessage(), orderStatus.getStatus(), user, orderStatus.getOrderId());
        return new ApiResponse(HttpStatus.OK.value(), AppConstant.SUCCESS, "Update successfully");
    }
}

// ... methods like viewOrders, getOrderDetails etc. ...

