package com.shareBuddy.repository;
 
import java.util.List; 
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shareBuddy.pojos.ReqBook;
  
 
 @Repository
public interface ReqBookRepository extends CrudRepository<ReqBook, Integer> {

	List<ReqBook> findByreqUserid(int id);
	
	
	 //public List<ReqBook> findBooks(int requserId);
	 
	 //List<ReqBook> getAll();
}
