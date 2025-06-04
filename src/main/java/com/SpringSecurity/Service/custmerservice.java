package com.SpringSecurity.Service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SpringSecurity.Entity.custmer;
import com.SpringSecurity.Repository.custmerrepo;

@Service
public class custmerservice implements UserDetailsService {
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Autowired
	private custmerrepo repo;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		custmer c = repo.findByEmail(email);
		
		  return new User(c.getEmail(), c.getPassword(), Collections.emptyList());
	}
	


	
	public boolean  savecustmer(custmer c)
	{
		
		String encodepwd = pwdEncoder.encode(c.getPassword());
		 c.setPassword(encodepwd);
		
		      custmer savedCustmer  = repo.save(c);
		     
		         return savedCustmer.getCid() != null ;    
	}
	
	
	    public custmer findByid(int cid)
	    {
	    	
	    	  return repo.findById(cid)
	    			     .orElseThrow(()-> new RuntimeException("custmer is not found  with id the:"+cid));
	
	

		      }
	  
	    
}