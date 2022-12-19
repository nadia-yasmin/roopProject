//package com.roop.User;
//import com.example.springtutorial.model.User;
//import com.example.springtutorial.repository.UserRepository;
//import com.example.springtutorial.service.UserService;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Controller
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@Builder
//public class UserController {
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    UserService userService;
//    //private final UserRepository userRepository;
//
//    @GetMapping("/signup")
//    public String getSignUp(Model model){
//        User user=new User();
//        model.addAttribute(user);
//        return "signup";
//    }
//
//    @GetMapping("/login")
//    public String getLogin(Model model){
//        User user=new User();
//        model.addAttribute(user);
//        return "login";}
//
//    @PostMapping("/post-signup")
//    public String postSignup(Model model, User user){
//        userRepository.save(user);
//        model.addAttribute(user);
//        return "login";
//    }
//
//    @PostMapping("/post-login")
//    public String postLogin(Model model,User user){
//        boolean validate=userService.loginValidate(user);
//        if (validate){
//            return "home";
//        }
//        else return "login";
//    }
//
//} 