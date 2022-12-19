package com.roop.admin.brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.roop.common.entity.Brand;
import com.roop.common.entity.Category;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
@DataJpaTest
@AutoConfigureTestDatabase
@Rollback(false)

public class BrandRepositoryTests {
@Autowired
private BrandRepository repo;
/*
@Test
public void testCreateBrand1() {
	Category creams=new Category(5);
	//Category shampoos =new Category(4);
	Brand bodyshop= new Brand("Body shop");
	bodyshop.getCategories().add(creams);
	//bodyshop.getCategories().add(shampoos);
	Brand savedBrand= repo.save(bodyshop);
	assertThat(savedBrand).isNotNull();
	assertThat(savedBrand.getId()).isGreaterThan(0);
}
@Test
public void testCreateBrand2() {
	Category creams =new Category(5);
	//Category shampoos =new Category(4);
	Brand bodyshop= new Brand("Body shop");
	bodyshop.getCategories().add(creams);
	//bodyshop.getCategories().add(shampoos);
	Brand savedBrand= repo.save(bodyshop);
	assertThat(savedBrand).isNotNull();
	assertThat(savedBrand.getId()).isGreaterThan(0);
}

*/
}