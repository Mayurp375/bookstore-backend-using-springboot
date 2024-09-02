package bookstore.bookstore.controller;

import bookstore.bookstore.entity.Cart;
import bookstore.bookstore.service.CartService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    //http://localhost:8080/api/cart/add
    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam Long bookId, @RequestParam Integer quantity, HttpHeaders httpHeaders) {
        String token = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);
        return new ResponseEntity<>(cartService.addToCart(token, bookId, quantity), HttpStatus.OK);
    }


    // ... Similarly, endpoints for viewCart, removeFromCart, etc. ...
}
