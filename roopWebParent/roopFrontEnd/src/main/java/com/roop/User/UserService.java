//package com.roop.User;
//
////import com.example.springtutorial.model.User;
////import com.example.springtutorial.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService {
//    @Autowired
//    UserRepository userRepository;
//    public boolean loginValidate(User user) {
//     List<User>userList =  userRepository.findByEmail(user.getEmail());
//     if (userList.isEmpty()){
//         return false;
//     }
//     else if (userList.get(0).getPassword().equals(user.getPassword())){
//         return true;
//     }
//     else return false;
//    }
//}