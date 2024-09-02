package bookstore.bookstore.repository;

import bookstore.bookstore.entity.Cart;
import bookstore.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CartRepository extends JpaRepository<Cart, Long> {
    // Fetch cart specific to a user. 
    // Assuming User entity has OneToOne relationship with Cart.
    Optional<Cart> findByUser(User user);
}
