package bookstore.bookstore.service;

import bookstore.bookstore.entity.Medicine;
import bookstore.bookstore.entity.Order;
import bookstore.bookstore.entity.OrderItem;
import bookstore.bookstore.entity.User;
import bookstore.bookstore.entity.dto.OrderItemDto;
import bookstore.bookstore.entity.dto.UpdateOrderStatus;
import bookstore.bookstore.entity.role.OrderStatus;
import bookstore.bookstore.exceptions.AuthTokenExpiredException;
import bookstore.bookstore.exceptions.UserNotFound;
import bookstore.bookstore.repository.MedicineRepository;
import bookstore.bookstore.repository.OrderRepository;
import bookstore.bookstore.repository.UserRepository;
import bookstore.bookstore.service.interfaces.OrderService;
import bookstore.bookstore.util.JWTToken;
import bookstore.bookstore.util.constant.AppConstant;
import bookstore.bookstore.util.date.DateUtil;
import bookstore.bookstore.util.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;
    private final JWTToken jwtToken;

    public OrderServiceImpl(OrderRepository orderRepository,
                            UserRepository userRepository,
                            JWTToken jwtToken, MedicineRepository medicineRepository) {
        this.orderRepository = orderRepository;

        this.userRepository = userRepository;
        this.jwtToken = jwtToken;
        this.medicineRepository = medicineRepository;
    }

    public void createOrder(Long userId, OrderItemDto orderItemsDTO) {
        User user = userRepository.findById(userId).orElseThrow(() ->
         new AuthTokenExpiredException(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), "User not found")
        );
        try{
            Order order = new Order();
            order.setUser(user);
            order.setOrderDate(DateUtil.currentDate());
            order.setAddress(orderItemsDTO.getAddress());
            order.setContact(orderItemsDTO.getContact());
            List<OrderItem> orderItems = new ArrayList<>();
            for (OrderItemDto.ItemsDto dto : orderItemsDTO.getItemsDtoList()) {
                Medicine medicine = medicineRepository.findById(dto.getMedicineId()).orElseThrow(() -> new RuntimeException("Medicine not found"));
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setMedicine(medicine);
                orderItem.setQuantity(dto.getQuantity());
                orderItem.setPrice(BigDecimal.valueOf(orderItemsDTO.getTotalAmount()));
                orderItems.add(orderItem);
            }
            order.setOrderStatus(OrderStatus.RECEIVED_BY_SELLER);
            //need change when order exicute need to get notify for seller order is came plz approve or reject and upldate status
            order.setOrderItems(orderItems);
            orderRepository.save(order);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findByIdWithOrderItems(orderId);
    }

    public List<Order> getOrderDetails(Long orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    public List<Order> getOrdersByUserId(String request) {
        Long userId = jwtToken.getUser(request);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findAllByUser(user);
    }

    public Map sellerOrder(String request) {
        Long userId = jwtToken.getUser(request);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        List<Order> orderList = orderRepository.findByUser(user);

        if (!orderList.isEmpty()) {
            return ApiResponse.responseFormatter(new ApiResponse(HttpStatus.OK.value(), AppConstant.SUCCESS, orderList));
        }
        return ApiResponse.responseFormatter(new ApiResponse(HttpStatus.NOT_FOUND.value(), AppConstant.FAILURE, "No order found"));
    }

    public ApiResponse updateOrderStatus(UpdateOrderStatus orderStatus) {
        User user = userRepository.findById(orderStatus.getSellerId()).orElse(null);
        if (user == null) {
            throw new UserNotFound(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), "User not found");
        }
        orderRepository.updateOrderDetails(orderStatus.getMessage(), orderStatus.getStatus(), orderStatus.getUserId(), orderStatus.getOrderId());
        return new ApiResponse(HttpStatus.OK.value(), AppConstant.SUCCESS, "Update successfully");
    }
}

// ... methods like viewOrders, getOrderDetails etc. ...

