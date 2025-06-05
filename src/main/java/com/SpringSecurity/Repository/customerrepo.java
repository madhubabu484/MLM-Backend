package com.SpringSecurity.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringSecurity.Entity.Customer;


@Repository
public interface customerrepo extends JpaRepository<Customer, String> {
	
	
	// Custamized Queries
	
	public  Customer findByEmail(String email);
	public Customer findTopByOrderByCidDesc();
}
