package bookstore.bookstore.repository;

import bookstore.bookstore.entity.Order;
import bookstore.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Fetch orders specific to a user.

    @Query(value = "select * from orders where user_id = ?1", nativeQuery = true)
    List<Order> findByUser(User user);

    @Query(value = "UPDATE orders SET message = ?1, orderStatus = ?2,user = ?3, WHERE id =?4", nativeQuery = true)
    @Transactional
    void updateOrderDetails(String message, String status, User userId, Long orderId);

    Order findFirstByUser(User user);

    List<Order> findAllByUser(User user);
}
