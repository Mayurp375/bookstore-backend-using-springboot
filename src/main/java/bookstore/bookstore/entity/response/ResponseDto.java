package bookstore.bookstore.entity.response;

import lombok.Data;

@Data
public class ResponseDto {
    private String status;
    private Integer code;
    private String message;

    public ResponseDto(String status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
