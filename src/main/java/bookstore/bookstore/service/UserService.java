package bookstore.bookstore.service;

import bookstore.bookstore.entity.User;
import bookstore.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;



    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    // Add more methods based on your requirements, e.g. updateUser, deleteUser, etc.
}
