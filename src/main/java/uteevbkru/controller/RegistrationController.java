package uteevbkru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uteevbkru.domain.Role;
import uteevbkru.domain.User;
import uteevbkru.repos.UserRepo;
//import uteevbkru.service.UserService;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }

    //    @Autowired
//    private UserService userService;

//    @PostMapping("/registration")
//    public String addUser(User user, Map<String, Object> model){
//        if(!userService.addUser(user)) {
//            model.put("message", "User exists!");
//            return "registration";
//        }
//
//        return "redirect:/login";
//    }

//    @GetMapping("/activate/{code}")
//    public String activate(Model model, @PathVariable String code) {
//        boolean isActivated = userService.activateUser(code);
//
//        if(isActivated) {
//            model.addAttribute("message", "User activate");
//        } else {
//            model.addAttribute("message", "Activation code isn't found" );
//        }
//
//        return "login";
//    }
}
