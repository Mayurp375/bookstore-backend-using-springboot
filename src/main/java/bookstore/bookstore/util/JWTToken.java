package bookstore.bookstore.util;

import bookstore.bookstore.entity.User;
import bookstore.bookstore.entity.role.UserRole;
import bookstore.bookstore.exceptions.AuthTokenExpiredException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTToken {
    private final String SECRET = "MAyur";
    // Token expiration time in milliseconds (e.g., 1 hour)
    private final long EXPIRATION_TIME = 10000000; // 1 hour in milliseconds

    String token;
    Date expiresAt = new Date(System.currentTimeMillis() + EXPIRATION_TIME);


    public String createToken(User user) {
        return JWT.create()
                .withClaim("userId", user.getId())
                .withClaim("Role",user.getRole().name())
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public Long decodeToken(String token) {

        long id = 0;
        if (token != null) {
            try {
                id = JWT.require(Algorithm.HMAC256(SECRET))
                        .build().verify(token)
                        .getClaim("userId").asLong();
            }catch (com.auth0.jwt.exceptions.TokenExpiredException e){
                throw new AuthTokenExpiredException(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), e.getMessage());
            }
        }
        return id;
    }
    public User decodeUserByToken(String token) {
    User user= new User();
        long id = 0;
        if (token != null) {
            id = JWT.require(Algorithm.HMAC256(SECRET))
                    .build().verify(token)
                    .getClaim("userId").asLong();
            String role = JWT.require(Algorithm.HMAC256(SECRET))
                    .build().verify(token)
                    .getClaim("Role").asString();
            user.setId(id);
            user.setRole(UserRole.valueOf(role));
        }
        return user;
    }
    public Long getUser(String request) {
        return decodeToken(request);
    }
}
