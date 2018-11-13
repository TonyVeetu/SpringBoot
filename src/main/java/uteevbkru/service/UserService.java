package uteevbkru.service;

import antlr.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uteevbkru.domain.Role;
import uteevbkru.domain.User;
import uteevbkru.repos.UserRepo;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    //@Autowired
    //private MailSender mailSender;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

//    public boolean addUser(User user){
//        User userFromDB = userRepo.findByUsername(user.getUsername());
//
//        if (userFromDB != null) {
//            return false;
//        }
//
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
//        user.setActivationCode(UUID.randomUUID().toString());
//
//        userRepo.save(user);
//
//        String userEamil = user.getEmail();
//        if ( !(userEamil.isEmpty() || userEamil == null)) {
//            String message = String.format(
//                "Hello, %s!\n" +
//                    "Welcom to SpringBoot, visit: http://localhost:8080/activate/%s",
//                    user.getUsername(),
//                    user.getActivationCode()
//            );
//            mailSender.send(user.getEmail(), "Activation Code", message);
//        }
//        return true;
//    }

//    public boolean activateUser(String code) {
//        User user = userRepo.findByActivationCode(code);
//
//        if(user == null) {
//            return false;
//        }
//
//        user.setActivationCode(null);
//
//        userRepo.save(user);
//
//        return false;
//    }
}
