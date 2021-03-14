package com.shareBuddy.service.impl;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shareBuddy.pojos.Book;
import com.shareBuddy.pojos.CartItem;
import com.shareBuddy.pojos.Order;
import com.shareBuddy.pojos.ShoppingCart;
import com.shareBuddy.pojos.User;
import com.shareBuddy.repository.OrderRepository;
import com.shareBuddy.service.CartItemService;
import com.shareBuddy.service.OrderService;
 

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	public synchronized Order createOrder(ShoppingCart shoppingCart,  User user) {
		Order order = new Order(); 
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItem cartItem : cartItemList) {
			Book book = cartItem.getBook();
			cartItem.setOrder(order);
			book.setInStockNumber(book.getInStockNumber() - cartItem.getQty());

		order.setCartItemList(cartItemList); 
		order.setOrderTotal(shoppingCart.getGrandTotal()); 
		order.setUser(user);
		order.setSellerId(book.getSellerId());
		order = orderRepository.save(order);

		}
		
		
		return order;
	}
	
	public Order findOne(Long id) {
		return ((OrderService) orderRepository).findOne(id);
	}

}
