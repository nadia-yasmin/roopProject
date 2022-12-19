package com.roop.admin.product;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;
import com.roop.common.entity.Brand;
import com.roop.common.entity.Category;
import com.roop.common.entity.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@Rollback(false)
public class ProductRepositoryTest {
@Autowired  private ProductRepository repo;
@Autowired private TestEntityManager entityManager;
@Test
public void testCreateProduct() {
	Brand brand= entityManager.find(Brand.class, 2);
	Category category= entityManager.find(Category.class, 9);
	Product product= new Product();
	product.setName("Dewy foundation");
	product.setAlias("Dewy foundation");
	product.setShortDescription("Its dewy");
	product.setFullDescription("Its new edition which is dewy");
	product.setBrand(brand);
	product.setCategory(category);
	product.setPrice(400);
	product.setCreatedTime(new Date());
	product.setUpdatedTime(new Date());
	Product savedProduct= repo.save(product);
	assertThat(savedProduct).isNotNull();
	assertThat(savedProduct.getId()).isGreaterThan(0);
	
}
}
