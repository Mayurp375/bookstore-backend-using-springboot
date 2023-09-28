package bookstore.bookstore.controller;

import bookstore.bookstore.entity.User;
import bookstore.bookstore.service.UserService;
import bookstore.bookstore.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private JWTToken jwtTokenUtil;


//
//    @PostMapping("register")
//    public ResponseEntity<User> registerUser(@RequestBody User user){
//        return ResponseEntity.ok(user);
//    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User registeredUser = userService.createUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User fetchedUser = userService.findUserByUsername(user.getUsername());

        if (fetchedUser != null && user.getPassword().equals(fetchedUser.getPassword())) {
            final String token = jwtTokenUtil.createToken(user.getId());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
