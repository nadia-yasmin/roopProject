package com.roop.admin.user;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.roop.common.entity.Role;
import com.roop.common.entity.user;

@DataJpaTest (showSql=false)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
@Autowired
private UserRepository repo;
@Autowired 
private TestEntityManager entityManager;
@Test
public void testCreateNewUserWithOneRole() {
	Role roleAdmin= entityManager.find(Role.class,1);
	user userNamHM= new user("nam@codejava.net","nam2020","nam","Ha mim");
	userNamHM.addRole(roleAdmin);
	user savedUser=repo.save(userNamHM);
	assertThat(savedUser.getId()).isGreaterThan(0);

}
@Test
public void testCreateNewUserWithTwoRole() {
user userRavi= new user("ravi@gmail.com","ravi2020","ravi","kumar");
Role roleEditor= new Role(13);
Role roleAssistant= new Role(15);
userRavi.addRole(roleEditor);
userRavi.addRole(roleAssistant);
user savedUser=repo.save(userRavi);
assertThat(savedUser.getId()).isGreaterThan(0);
}
@Test
public void testListAllUsers() {
	Iterable<user> listUsers=repo.findAll(); 
	listUsers.forEach(user -> System.out.println(user));
}
@Test
public void testGetUserById() {
	user userNam=repo.findById(4).get();
	System.out.println(userNam);
	assertThat(userNam).isNotNull();
}
@Test
public void testUpdateUserDetails() {
	user userNam= repo.findById(4).get();
	userNam.setEnabled(true);
	userNam.setEmail("namjavaprogrammar@gmi.com");
	repo.save(userNam);
}
@Test
public void testUpdateUserRoles() {
	user userRavi= repo.findById(9).get();
	Role roleEditor = new Role(13);
	Role roleSalesperson = new Role(12);
	userRavi.getRoles().remove(roleEditor);
	userRavi.addRole(roleSalesperson);
	repo.save(userRavi); 
}
@Test
public void testDeleteUser() {
	Integer userId=9;
	repo.deleteById(userId);
	repo.findById(userId);
	
}
@Test 
public void testGetUserByEmail() {
	String email="yasminnadia973@gmail.com";
	user user= repo.getUserByEmail(email);
	assertThat(user).isNotNull();
	
}
@Test 
public void testCountById() {
	Integer id=4;
	Long countById=repo.countById(id);
	assertThat(countById).isNotNull().isGreaterThan(0);
	
}
@Test 
public void testDisableUser() {
	Integer id=14;
	repo.updateEnabledStatus(id, false);
		
}
@Test 
public void testEnableUser() {
	Integer id=13;
	repo.updateEnabledStatus(id, true);
	
	
}
@Test 
public void testListFirstPage() {
	int pageNumber=0;
	int pageSize=4;
	Pageable pageable= PageRequest.of(pageNumber, pageSize);
	Page<user> page=repo.findAll(pageable);
	List<user> listUsers= page.getContent();
	listUsers.forEach(user -> System.out.println(user));
	assertThat(listUsers.size()).isEqualTo(pageSize);
}

}
