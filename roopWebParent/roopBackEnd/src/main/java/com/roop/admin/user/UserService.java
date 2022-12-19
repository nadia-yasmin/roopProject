package com.roop.admin.user;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


import com.roop.common.entity.Role;
import com.roop.common.entity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
@Transactional

public class UserService {
	public static final int USERS_PER_PAGE =4;
@Autowired
private UserRepository userRepo;
@Autowired
private RoleRepository roleRepo;
@Autowired
private PasswordEncoder passwordEncoder;
public List<user> listAll(){
	return (List<user>) userRepo.findAll();
}
public Page<user> listByPage(int pageNum){
	Pageable pageable= PageRequest.of(pageNum-1, USERS_PER_PAGE );
	return userRepo.findAll(pageable);
}
	public List<Role> listRoles(){
		return (List<Role>) roleRepo.findAll();
		
	}
	
		public user save(user user) {
		// TODO Auto-generated method stub
			encodePassword(user);
			return userRepo.save(user);
		
	}
		private void encodePassword(user user) {
			String encodedPassword=passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
		}
public boolean  isEmailUnique(Integer id,String email) {
	user userByEmail= userRepo.getUserByEmail(email);
	if(userByEmail==null) return true;
	boolean isCreatingNew=(id==null);
	if(isCreatingNew) {
		if(userByEmail != null) return false;
	}else {
		if(userByEmail.getId()!=id) {
			return false;
		}
	}
	return true;
}

public user get(Integer id) throws UserNotFoundException {
	// TODO Auto-generated method stub
	try {
		return userRepo.findById(id).get();
			}
	catch(NoSuchElementException ex){
		throw new UserNotFoundException("Could not find any user with ID " + id);
		
	}
}
public void delete(Integer id) throws UserNotFoundException{
	Long countById=userRepo.countById(id);
	if(countById==null||countById==0) {
		throw new UserNotFoundException("Could Not Find any User With Id "+id);
	}
	userRepo.deleteById(id);
}
public void updateEnabledStatus(Integer id, boolean enabled) {
	userRepo.updateEnabledStatus(id, enabled);
}
/*

public boolean loginValidate(user user) {
 List<user>userList =  userRepo.findByEmail(user.getEmail());
 if (userList.isEmpty()){
     return false;
 }
 else if (userList.get(0).getPassword().equals(user.getPassword())){
     return true;
 }
 else return false;
} */
}
  
