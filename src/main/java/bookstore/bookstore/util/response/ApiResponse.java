package bookstore.bookstore.util.response;

import bookstore.bookstore.util.constant.AppConstant;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ApiResponse {
    private int code;
    private String status;
    private String message;
    private List data;

    public ApiResponse(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
    public ApiResponse(int code, String status, List data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public static Map<String, Object> responseFormatter(ApiResponse apiResponse){
        Map<String, Object> map = new HashMap<>();
        map.put(AppConstant.DATA,apiResponse.getData());
        map.put(AppConstant.CODE,apiResponse.getCode());
        map.put(AppConstant.STATUS,apiResponse.getStatus());
        map.put(AppConstant.MESSAGE,apiResponse.getMessage());
        return map;
    }

}
