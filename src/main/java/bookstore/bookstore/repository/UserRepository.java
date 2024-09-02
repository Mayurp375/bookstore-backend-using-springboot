package bookstore.bookstore.repository;

import bookstore.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface  UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @Query(value = "select * from users where email = ?1 and password = ?2 and role = ?3",nativeQuery = true)
    Optional<User> findByEmailAndPasswordAndUserRole(String email,String password,String role);
}
