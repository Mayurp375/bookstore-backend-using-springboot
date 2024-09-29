package bookstore.bookstore.controller;

import bookstore.bookstore.entity.User;
import bookstore.bookstore.entity.dto.LoginDto;
import bookstore.bookstore.entity.dto.RegisterDto;
import bookstore.bookstore.service.UserServiceImpl;
import bookstore.bookstore.service.firebaseInterface.UserServiceImplForFireBase;
import bookstore.bookstore.service.interfaces.UserService;
import bookstore.bookstore.util.JWTToken;
import bookstore.bookstore.util.constant.AppConstant;
import bookstore.bookstore.util.constant.MessageConstant;
import bookstore.bookstore.util.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin("http://localhost:4200/")
@Slf4j
public class UserController {
    private final UserService userService;
    private final JWTToken jwtTokenUtil;

    public UserController(UserService userService, JWTToken jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    //http://localhost:8080/register
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDto user) {
        log.info("request :: {}", user);
        User registeredUser = userService.createUser(user);
        if (Objects.isNull(registeredUser)) {
            return ResponseEntity.ok(new ApiResponse(HttpStatus.FOUND.value(), AppConstant.SUCCESS, "Already exist " + user.getUsername()));
        }
        return ResponseEntity.ok(new ApiResponse(HttpStatus.CREATED.value(), AppConstant.SUCCESS, "Successfully created user : " + registeredUser.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDto user) {
        log.info("login request :: {}", user);
        User fetchedUser = userService.findUserByUsername(user);

        Map<String, Object> response = new HashMap<>();
        response.put(AppConstant.CODE, HttpStatus.OK.value());
        response.put(AppConstant.STATUS, AppConstant.SUCCESS);
        if (fetchedUser == null) {
            response.put(AppConstant.MESSAGE, MessageConstant.INVALID_CREDENTIALS);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        final String token = jwtTokenUtil.createToken(fetchedUser);
        response.put(AppConstant.MESSAGE, MessageConstant.LOGIN_SUCCESSFUL);
        response.put(AppConstant.TOKEN, token);
        return ResponseEntity.ok(response);
    }
}
