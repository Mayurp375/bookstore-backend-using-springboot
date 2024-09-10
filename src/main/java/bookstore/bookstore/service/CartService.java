//package bookstore.bookstore.service;
//
//import bookstore.bookstore.entity.Medicine;
//import bookstore.bookstore.entity.User;
//import bookstore.bookstore.repository.MedicineRepository;
//import bookstore.bookstore.repository.UserRepository;
//import bookstore.bookstore.util.JWTToken;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CartService {
//
//    private final CartRepository cartRepository;
//    private final MedicineRepository bookRepository;
//    private final UserRepository userRepository;
//    private final JWTToken jwtToken;
//
//    public CartService(CartRepository cartRepository,
//                       MedicineRepository bookRepository,
//                       UserRepository userRepository,JWTToken jwtToken) {
//        this.cartRepository = cartRepository;
//        this.bookRepository = bookRepository;
//        this.userRepository = userRepository;
//        this.jwtToken = jwtToken;
//    }
//
//    public Cart addToCart(String token, Long bookId, int quantity) {
//        Long userId = jwtToken.decodeToken(token);
//        User user = userRepository.findById(userId).orElse(null);
//        Medicine book = bookRepository.findById(bookId).orElse(null);
//
//        if (user == null || book == null) {
//            return null;
//        }
//
//        Cart cart = cartRepository.findByUser(user).orElse(new Cart(user));
//        CartItem cartItem = new CartItem(book, quantity);
//        cart.getItems().add(cartItem);
//        return cartRepository.save(cart);
//    }
//}
