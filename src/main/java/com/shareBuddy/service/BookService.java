package com.shareBuddy.service;

import java.util.List;

import com.shareBuddy.pojos.Book;
import com.shareBuddy.pojos.ReqBook;
import com.shareBuddy.pojos.User;

public interface BookService {

	List<Book> findAll();

	Book findOne(Integer id);

	List<Book> findByCategory(String category);

	List<Book> blurrySearch(String keyword);
	
	ReqBook save(ReqBook reqbook);

	User findBySellerId(int ids);
}
