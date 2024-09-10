package bookstore.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

//    @ResponseStatus(value = HttpStatus.FOUND,
//            reason = "Data integrity violation")  // 409
    @ExceptionHandler(value = UserNotFound.class)
    public ResponseEntity<ErrorClass> userNotFound(UserNotFound e) {
        return new ResponseEntity<>(new ErrorClass(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), e.getMessage()),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = AuthTokenExpiredException.class)
    public ResponseEntity<ErrorClass> tokenExpired(AuthTokenExpiredException e) {
        return new ResponseEntity<>(new ErrorClass(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), e.getMessage()),HttpStatus.UNAUTHORIZED);
    }
}
