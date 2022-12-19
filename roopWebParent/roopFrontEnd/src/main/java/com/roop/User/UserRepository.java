//package com.roop.User;
//
////import com.example.springtutorial.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface UserRepository extends JpaRepository<User, Integer> {
//
//    @Query(value ="select * from user where email=:email" ,nativeQuery = true)
//    List<User> findByEmail(String email);
//}