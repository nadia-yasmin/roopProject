package com.roop.admin.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
@RestController 
public class UserRestController {
@Autowired
private UserService service;
@PostMapping("/users/check_email")
public String checkDuplicateEmail(@Param("id") Integer id, @Param("email") String email) {
	return service.isEmailUnique(id,email)? "OK": "Duplicated";
}

}
