package com.SpringSecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringSecurity.Entity.Custmer;

@Repository
public interface custmerrepo extends JpaRepository<Custmer, Integer> {
	
	
	// Custamized Quries
	
	  public Custmer  findByEmail(String email);
	  
	  public Custmer  findByName(String name);

}
