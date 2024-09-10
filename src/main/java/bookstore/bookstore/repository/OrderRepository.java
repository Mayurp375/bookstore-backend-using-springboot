package bookstore.bookstore.repository;

import bookstore.bookstore.entity.Order;
import bookstore.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Fetch orders specific to a user.

    @Query(value = "select * from orders where user_id = ?1", nativeQuery = true)
    List<Order> findByUser(User user);

    @Modifying
    @Query(value = "UPDATE orders SET message = ?1, order_status = ?2, user_id = ?3 WHERE id = ?4", nativeQuery = true)
    @Transactional
    void updateOrderDetails(String message, String status, Long userId, Long orderId);

    List<Order> findAllByUser(User user);

    @Query("SELECT o FROM Order o JOIN FETCH o.orderItems WHERE o.id = :orderId")
    Order findByIdWithOrderItems(@Param("orderId") Long orderId);

//    @Query(value = "select * from orders where id = ?1", nativeQuery = true)
//    List<Order> findByOrderId(Long orderId);

    @Query(value = "select * from orders o \n" +
//            "INNER join users u on o.user_id = u.id \n" +
//            "INNER join order_items oi  on oi.order_id = o.id \n" +
//            "INNER join medicine m on m.id = oi.medicine_id \n" +
            "where o.id = ?1", nativeQuery = true)
    List<Order> findByOrderId(Long orderId);
}
