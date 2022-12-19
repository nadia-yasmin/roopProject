package com.roop.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.roop.common.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE p.enabled=true"
			+ "AND (p.category.id=?1 OR p.category.allParentIDs LIKE %?2%) "
	+" ORDER BY p.name ASC")
public Page<Product> listByCategory(Integer categoryId, String categoryIDMatch, Pageable pageable);

@Query(value="SELECT * FROM products", nativeQuery= true)
public List<Product> findAll();
public Product findByAlias(String alias);
}