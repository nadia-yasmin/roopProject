package com.roop.admin.product;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.roop.common.entity.Product;
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

}
