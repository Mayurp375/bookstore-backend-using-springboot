package bookstore.bookstore.entity;

import bookstore.bookstore.entity.dto.RegisterDto;
import bookstore.bookstore.entity.role.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;  // Assuming an Enum named UserRole

    public User(RegisterDto dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.role = UserRole.valueOf(dto.getRole());
    }

// ... getters, setters, constructors ...
}
