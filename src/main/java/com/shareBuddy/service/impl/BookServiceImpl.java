package com.shareBuddy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shareBuddy.pojos.Book;
import com.shareBuddy.pojos.ReqBook;
import com.shareBuddy.pojos.User;
import com.shareBuddy.repository.BookRepository;
import com.shareBuddy.service.BookService;
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> findAll() {
		return (List<Book>) bookRepository.findAll();
	}

	@Override
	public Book findOne(Integer id) {
		Optional<Book> bookResponse = bookRepository.findById(id);
		Book book = bookResponse.get();
		return book;
	}

	@Override
	public List<Book> findByCategory(String category){
		List<Book> bookList = bookRepository.findByCategory(category);
		
		List<Book> activeBookList = new ArrayList<>();
		
		for (Book book: bookList) {
			if(book.isActive()) {
				activeBookList.add(book);
			}
		}
		
		return activeBookList;
	}

	@Override
	public List<Book> blurrySearch(String title) {
		List<Book> bookList = bookRepository.findByTitleContaining(title);
		List<Book> activeBookList = new ArrayList<>();
		 for (Book book: bookList) {
			if(book.isActive()) {
				activeBookList.add(book);
			}
		}
		
		return activeBookList;
	}

	@Override
	public ReqBook save(ReqBook reqbook) {
		return bookRepository.save(reqbook);
	}

	@Override
	public User findBySellerId(int ids) {
		// TODO Auto-generated method stub
		return bookRepository.findBySellerId(ids);
	}

}
