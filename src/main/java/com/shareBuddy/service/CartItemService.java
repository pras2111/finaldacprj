package com.shareBuddy.service;

import java.util.List;

import com.shareBuddy.pojos.Book;
import com.shareBuddy.pojos.CartItem;
import com.shareBuddy.pojos.ShoppingCart;
import com.shareBuddy.pojos.User;

public interface CartItemService {

	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

	CartItem updateCartItem(CartItem cartItem);

	CartItem addBookToCartItem(Book book, User user, int parseInt);

	CartItem findById(Long cartItemId);

	void removeCartItem(CartItem findById);
}
