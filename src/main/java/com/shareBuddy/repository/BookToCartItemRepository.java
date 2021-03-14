package com.shareBuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.shareBuddy.pojos.BookToCartItem;
import com.shareBuddy.pojos.CartItem;

@Transactional
public interface BookToCartItemRepository extends  CrudRepository<BookToCartItem, Long>{

	void deleteByCartItem(CartItem cartItem);

}
