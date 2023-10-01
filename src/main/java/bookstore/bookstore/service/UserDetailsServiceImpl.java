//package bookstore.bookstore.service;
//
//
//
//
//import bookstore.bookstore.entity.User;
//import bookstore.bookstore.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//
//    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
//        return authorities;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                getAuthorities(user)
//        );
//    }
//
//}
