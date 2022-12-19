package com.roop.shoppingcart;

import org.springframework.data.repository.CrudRepository;

import com.roop.common.entity.cartItem;

public interface CartItemRepository extends CrudRepository<cartItem, Integer> {

}
