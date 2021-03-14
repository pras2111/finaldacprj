package com.shareBuddy.repository;

import org.springframework.data.repository.CrudRepository;

import com.shareBuddy.pojos.Order;
 

public interface OrderRepository extends CrudRepository<Order, Long>{

}
