package bookstore.bookstore.repository;

import bookstore.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // This can be used for authentication to fetch user by username
    Optional<User> findByUsername(String username);
}
