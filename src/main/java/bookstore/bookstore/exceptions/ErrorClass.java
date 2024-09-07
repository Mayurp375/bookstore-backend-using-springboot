package bookstore.bookstore.exceptions;

import lombok.Data;

@Data
public class ErrorClass {
    private int code;
    private String status;
    private String message;

    public ErrorClass(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
