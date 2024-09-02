package bookstore.bookstore.service;

import bookstore.bookstore.entity.User;
import bookstore.bookstore.entity.dto.LoginDto;
import bookstore.bookstore.entity.dto.RegisterDto;
import bookstore.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(RegisterDto user) {
        Optional<User> existUser = userRepository.findByEmailAndPasswordAndUserRole(user.getEmail(), user.getPassword(), String.valueOf(user.getRole()));
        if (existUser.isPresent()){
            return null;
        }
        return userRepository.save(new User(user));
    }

    public User findUserByUsername(LoginDto loginDto) {
        Optional<User> user = userRepository.findByEmailAndPasswordAndUserRole(loginDto.getEmail(), loginDto.getPassword(), loginDto.getRole());
        return user.orElse(null);
    }

    // Add more methods based on your requirements, e.g. updateUser, deleteUser, etc.
}
