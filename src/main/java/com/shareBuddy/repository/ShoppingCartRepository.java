package com.shareBuddy.repository;

import org.springframework.data.repository.CrudRepository;

import com.shareBuddy.pojos.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
