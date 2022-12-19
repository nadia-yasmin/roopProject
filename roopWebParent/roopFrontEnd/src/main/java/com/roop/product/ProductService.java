package com.roop.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.roop.common.entity.Product;

@Service
public class ProductService{
	public static final int PRODUCTS_PER_PAGE=10;
	@Autowired private ProductRepository repo;
	public Page<Product> listByCategory(int pageNum,  Integer categoryId){
		String categoryIdMatch= "-"+ String.valueOf(categoryId)+"-";
		Pageable pageable= PageRequest.of(pageNum-1, PRODUCTS_PER_PAGE);
		return repo.listByCategory(categoryId, categoryIdMatch, pageable);
	}
	public List<Product> getAllProduct(){
		return repo.findAll();
	}
	public Product getProduct(String alias) throws ProductNotFoundException {
		Product product= repo.findByAlias(alias);
		if(product==null) {
			throw new ProductNotFoundException("Could not find any product with alias"+ alias);
		}
		return product;
		}
}