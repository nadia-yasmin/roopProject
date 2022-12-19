package com.roop.admin.user;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.roop.common.entity.user;
public interface UserRepository extends PagingAndSortingRepository<user, Integer> {
 @Query("SELECT u FROM user u WHERE u.email= :email") 
public user getUserByEmail(@Param("email") String email);
 public Long countById(Integer id);
 @Query("UPDATE user u SET u.enabled = ?2 WHERE u.id=?1")
 @Modifying 
 public void updateEnabledStatus(Integer id, boolean enabled);
 /*
 @Query(value ="select * from user where email=:email" ,nativeQuery = true)
 List<user> findByEmail(String email);
 */
}
