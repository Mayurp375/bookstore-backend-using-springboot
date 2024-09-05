package bookstore.bookstore.repository;

import bookstore.bookstore.entity.Order;
import bookstore.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Fetch orders specific to a user.
    List<Order> findByUser(User user);
    Order findFirstByUser(User user);
}
