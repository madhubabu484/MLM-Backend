package com.SpringSecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringSecurity.Entity.custmer;

@Repository
public interface custmerrepo extends JpaRepository<custmer, Integer> {
	
	
	// Custamized Quries
	
	  public custmer  findByEmail(String email);
	  

}
