package bookstore.bookstore.entity.dto;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class RegisterDto {

    String username;
    String password;
    String email;
    @Enumerated(EnumType.STRING)
    private String role;  // Assuming an Enum named UserRole
    // ... getters, setters, constructors ...
}
