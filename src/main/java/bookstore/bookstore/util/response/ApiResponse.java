package bookstore.bookstore.util.response;

import lombok.Data;

@Data
public class ApiResponse {
    private int code;
    private String status;
    private String message;

    public ApiResponse(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
