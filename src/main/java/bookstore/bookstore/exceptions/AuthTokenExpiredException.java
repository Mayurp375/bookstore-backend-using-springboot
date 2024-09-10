package bookstore.bookstore.exceptions;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthTokenExpiredException extends RuntimeException{
    private final int code;
    private final String status;
    private final String message;

    public AuthTokenExpiredException(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
