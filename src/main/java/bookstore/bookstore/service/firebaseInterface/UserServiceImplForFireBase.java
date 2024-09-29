package bookstore.bookstore.service.firebaseInterface;

import bookstore.bookstore.entity.User;
import bookstore.bookstore.entity.dto.LoginDto;
import bookstore.bookstore.entity.dto.RegisterDto;
import bookstore.bookstore.service.interfaces.UserService;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImplForFireBase implements UserService {


    @Override
    public User findUserByUsername(LoginDto loginDto) {
        return null;
    }


    @Autowired
    private FirebaseAuth firebaseAuth; // FirebaseAuth instance from Firebase Admin SDK

    @Override
    public User createUser(RegisterDto user) {
      //  try {
//            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
//                    .setEmail(user.getEmail())
//                    .setEmailVerified(false)
//                    .setPassword(user.getPassword())
//                    .setDisplayName(user.getUsername())
//                    .setDisabled(false);

           // UserRecord userRecord = firebaseAuth.createUser(request);
            // Create and return a User entity or DTO for your local use
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            //newUser.setFirebaseUid(userRecord.getUid()); // Firebase UID

            return newUser;
       // } catch (FirebaseAuthException e) {
            // Handle the exception
        //    throw new RuntimeException("Error creating user in Firebase", e);
       // }
    }



}
