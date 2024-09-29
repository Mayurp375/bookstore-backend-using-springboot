package bookstore.bookstore.service.interfaces;

import bookstore.bookstore.entity.User;
import bookstore.bookstore.entity.dto.LoginDto;
import bookstore.bookstore.entity.dto.RegisterDto;

public interface UserService {
    User createUser(RegisterDto user);
    User findUserByUsername(LoginDto loginDto);
}
