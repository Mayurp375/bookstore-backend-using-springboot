package bookstore.bookstore.entity;

import bookstore.bookstore.entity.role.UserRole;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;  // Assuming an Enum named UserRole

    // ... getters, setters, constructors ...
}
