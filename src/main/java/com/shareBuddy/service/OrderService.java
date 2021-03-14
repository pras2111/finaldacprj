package com.shareBuddy.service;

import com.shareBuddy.pojos.Order;
import com.shareBuddy.pojos.ShoppingCart;
import com.shareBuddy.pojos.User;

public interface OrderService {
	Order createOrder(ShoppingCart shoppingCart, User user);
	 Order findOne(Long id);
}
