package bookstore.bookstore.entity;

import bookstore.bookstore.entity.dto.RegisterDto;
import bookstore.bookstore.entity.role.UserRole;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@ToString
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;  // Assuming an Enum named UserRole
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private List<Order> orders;

    public User(RegisterDto dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.role = UserRole.valueOf(dto.getRole());
    }
}
