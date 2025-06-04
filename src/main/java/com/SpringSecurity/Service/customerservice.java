package com.SpringSecurity.Service;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.SpringSecurity.Entity.Customer;
import com.SpringSecurity.Repository.customerrepo;



@Service
public class customerservice implements UserDetailsService {
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Autowired
	private customerrepo repo;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Customer c = repo.findByEmail(email);
				
		  return new User(c.getEmail(), c.getPassword(), Collections.emptyList());
	}
	


	
	public boolean  savecustomer(Customer c)
	{
	      Customer lastId=repo.findTopByOrderByCidDesc();
	                
	      int number=1;
	                
	      if(lastId != null) {
	    	  String id=lastId.getCid();
		      String subid= id.substring(3);
		      number= Integer.parseInt(subid) +1;
		      }
	      
	      String new_id= String.format("MLM%05d", number);
	           c.setCid(new_id);
	      
	      String encodepwd = pwdEncoder.encode(c.getPassword());
		       c.setPassword(encodepwd);
		
	      Customer savedCustmer  = repo.save(c);
		     
		        return savedCustmer.getCid() != null ;    
	}
	
}
