package com.shareBuddy.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.shareBuddy.pojos.CartItem;
import com.shareBuddy.pojos.ShoppingCart;

@Transactional
public interface CartItemRepository extends CrudRepository<CartItem, Long> {

	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
}
