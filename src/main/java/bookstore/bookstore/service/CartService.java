package bookstore.bookstore.service;

import bookstore.bookstore.entity.Book;
import bookstore.bookstore.entity.Cart;
import bookstore.bookstore.entity.CartItem;
import bookstore.bookstore.entity.User;
import bookstore.bookstore.repository.BookRepository;
import bookstore.bookstore.repository.CartRepository;
import bookstore.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;


    public Cart addToCart(Long userId, Long bookId, int quantity) {
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);


        if (user == null || book == null) {

            return null;
        }

        Cart cart = cartRepository.findByUser(user).orElse(new Cart(user));
        CartItem cartItem = new CartItem(book, quantity);
        cart.getItems().add(cartItem);

        return cartRepository.save(cart);
    }


}
