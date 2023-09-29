package bookstore.bookstore.controller;

import bookstore.bookstore.entity.User;
import bookstore.bookstore.entity.dto.LoginDto;
import bookstore.bookstore.service.UserService;
import bookstore.bookstore.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200/")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private JWTToken jwtTokenUtil;

    //http://localhost:8080/register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User registeredUser = userService.createUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }


//    http://localhost:8080/login
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginDto user) {
//        User fetchedUser = userService.findUserByUsername(user.getEmail());
//
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
//        }
//
//        if ( user.getPassword().equals(fetchedUser.getPassword())) {
//            final String token = jwtTokenUtil.createToken(fetchedUser.getId());
//            return ResponseEntity.ok(token);
//        }
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDto user) {
        User fetchedUser = userService.findUserByUsername(user.getEmail());

        Map<String, Object> response = new HashMap<>();

        if (user == null) {
            response.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        if (user.getPassword().equals(fetchedUser.getPassword())) {
            final String token = jwtTokenUtil.createToken(fetchedUser.getId());
            response.put("message", "Login successful");
            response.put("token", token);
            return ResponseEntity.ok(response);
        }

        response.put("message", "Invalid credentials");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }



}
