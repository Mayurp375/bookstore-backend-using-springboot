package bookstore.bookstore.controller;

import bookstore.bookstore.entity.Cart;
import bookstore.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    //http://localhost:8080/api/cart/add
    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam Long userId,
                                          @RequestParam Long bookId,
                                          @RequestParam int quantity) {
        return new ResponseEntity<>(cartService.addToCart(userId, bookId, quantity), HttpStatus.OK);
    }

    // ... Similarly, endpoints for viewCart, removeFromCart, etc. ...
}
