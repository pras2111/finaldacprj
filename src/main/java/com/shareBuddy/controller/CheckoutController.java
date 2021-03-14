package com.shareBuddy.controller;

import java.security.Principal; 
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shareBuddy.pojos.Order; 
import com.shareBuddy.pojos.ShoppingCart; 
import com.shareBuddy.pojos.BillingAddress;
import com.shareBuddy.pojos.CartItem; 
import com.shareBuddy.pojos.ShippingAddress; 
import com.shareBuddy.pojos.User;
import com.shareBuddy.service.BookService;
import com.shareBuddy.service.CartItemService;
import com.shareBuddy.service.OrderService;
import com.shareBuddy.service.ShoppingCartService;
import com.shareBuddy.service.UserService;
import com.shareBuddy.utility.MailContructor; 

@Controller
public class CheckoutController {
	
	public ShippingAddress shippingAddress = new ShippingAddress();
	public BillingAddress billingAddress = new BillingAddress(); 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private BookService bookService;

	@SuppressWarnings("unused")
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private OrderService	orderService;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailContructor mailConstructor;
	
	@SuppressWarnings("unused")
	@RequestMapping("/checkout")
	public String checkout(
			@RequestParam("id")Long cartId,
			@RequestParam(value = "missingRequiredField", required=false) boolean missingRequiredField,
			Model model, Principal principal
			) {
		System.out.print("inside checkout");
		User user = userService.findByUsername(principal.getName());
		//cartItemService.
		if(cartId != user.getShoppingCart().getId()) {
			return "badRequestPage";
		}
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
		if(cartItemList.size()==0) {
			model.addAttribute("emptyCart", true);
			
			return "forward:/shoppingCart/cart";
		}
		
		for(CartItem cartItem : cartItemList) {
			if(cartItem.getBook().getInStockNumber() < cartItem.getQty()) {
				model.addAttribute("notEnoughStock",true);
				return "forward:/shoppingCart/cart";
			}
		}
		
		ShoppingCart shoppingCart = userService.findByUsername(principal.getName()).getShoppingCart();

		

		model.addAttribute("shoppingCart", user.getShoppingCart());
		model.addAttribute("cartItemList", cartItemList);
		model.addAttribute("classActiveShipping",true);  
		
		if(missingRequiredField) {
			model.addAttribute("missingRequiredField",true);
			
		}
	 	
		return "checkout";
	}
	
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String checkoutPost(@RequestParam("id")int id,@RequestParam("id")int ids ,Principal principal, Model model) {
		ShoppingCart shoppingCart = userService.findByUsername(principal.getName()).getShoppingCart();

		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		model.addAttribute("cartItemList", cartItemList);
 
		//User
		User user = bookService.findBySellerId(ids);
		Order order = orderService.createOrder(shoppingCart, user);
		//Book bk = cartItemList.
		
		
		//mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, order, Locale.ENGLISH));
		mailSender.send(mailConstructor.constructInterestEmail(user, order, Locale.ENGLISH));
		 
		 
		
		return "orderSubmittedPage";
	}
	
	 
}
