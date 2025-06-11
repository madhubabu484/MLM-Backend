package com.SpringSecurity.Service;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.SpringSecurity.CustomExceptions.CustmerNameNotFoundException;
import com.SpringSecurity.Entity.Custmer;
import com.SpringSecurity.Repository.custmerrepo;

@Service
public class custmerservice implements UserDetailsService {
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Autowired
	private custmerrepo repo;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Custmer c = repo.findByEmail(email);
		
		  return new User(c.getEmail(), c.getPassword(), Collections.emptyList());
	}
	


	
	public boolean  savecustmer(Custmer c)
	{
		
		String encodepwd = pwdEncoder.encode(c.getPassword());
		 c.setPassword(encodepwd);
		
		      Custmer savedCustmer  = repo.save(c);
		     
		         return savedCustmer.getCid() != null ;    
	}
	
	
	    public Custmer findByid(int cid) {
	    	Custmer c1 = repo.findById(cid).orElse(null);

	        if (c1 == null) {
	            throw new RuntimeException("Custmer not found with ID: " + cid);
	        }

	        return c1;
	    }
	    
	    public Custmer findByName(String name) throws CustmerNameNotFoundException
	    {
	    	
	    	Custmer c2 = repo.findByName(name);
	    	
	    	if(c2!=null)
	    	{
	    		return c2;
	    	}
	    	
	    	else {
	    		    
	    		    throw new CustmerNameNotFoundException("Custmer Name not Found with the name :"+name);
	    	}
	    }
}

