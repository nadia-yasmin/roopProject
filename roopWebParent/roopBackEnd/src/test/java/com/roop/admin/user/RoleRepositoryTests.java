package com.roop.admin.user;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import org.springframework.test.annotation.Rollback;

import com.roop.common.entity.Role;



@DataJpaTest  
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
   @Autowired   
   private RoleRepository repo;
   @Test
   public void testCreateFirstRole() {
	   Role roleAdmin= new Role("Admin","manage everything");
	   Role savedRole=repo.save(roleAdmin);
	   assertThat(savedRole.getId()).isGreaterThan(0);
  } 
@Test
public void testCreateRestRole() {
	   Role rolesalesperson= new Role("Salesperson","mangage product price,"+"customer shipping, orders and sales report");
	   Role roleEditor= new Role("editor","Manage categories, brands,products, articles and manus");
	   Role roleShipper= new Role("Shipper", "view orders, view products,update order status");
	   Role roleAssistant= new Role("Assistant","manage question and reviews");
	   repo.saveAll(List.of(rolesalesperson,roleEditor,roleShipper,roleAssistant));

   }
}

