package com.SpringSecurity.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SpringSecurity.Entity.Custmer;
import com.SpringSecurity.Repository.custmerrepo;

@Service
public class VerifyPassword {
	
	    @Autowired
	    private custmerrepo repo;
	    
	    @Autowired
	    private BCryptPasswordEncoder Encoder;
	
	   public boolean verifypassword(String email , String rawpassword)
	   {
		   
		    Custmer cu = repo.findByEmail(email);
		   
		      if(cu==null)
		      {
		    	  throw new UsernameNotFoundException("Customer not found with email: " + email);
		      }

		    
				return Encoder.matches(rawpassword, cu.getPassword());
		
			}
		  
		      
	   
	
	
	

}
