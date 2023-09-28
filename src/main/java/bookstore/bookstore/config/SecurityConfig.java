//package bookstore.bookstore.config;
//
////import bookstore.bookstore.util.JwtAuthenticationEntryPoint;
////import bookstore.bookstore.util.JwtRequestFilter;
//import bookstore.bookstore.util.JWTToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
////
////    @Autowired
////    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
////
////    @Autowired
////    private JwtRequestFilter jwtRequestFilter;
//    @Autowired
//    private JWTToken jwtToken;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/api/user/**").permitAll()  // Assuming "/api/user/**" is for user registration/login etc.
//            .antMatchers("/api/admin/**").hasRole("ADMIN")
//            .anyRequest().authenticated()
//            .and()
//            .exceptionHandling()
//            .and().sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager();
//        inMemoryUserDetailsManager.userExists(jwtToken.decodeToken());
//    }
//}
