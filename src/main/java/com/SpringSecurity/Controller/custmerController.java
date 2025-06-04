package com.SpringSecurity.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SpringSecurity.Entity.custmer;
import com.SpringSecurity.Service.custmerservice;

@RestController
public class custmerController {

	@Autowired
	private custmerservice service;

	@Autowired
	private AuthenticationManager authmanager;

	@PostMapping("/login")
	public ResponseEntity<String> logincustmer(@RequestBody custmer cu)
	{
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(cu.getEmail(), cu.getPassword());

		// Now we are checking auttntication for use authmanager responsible for checking authenticationManager.
		System.out.println(token);
		Authentication  authenticate = authmanager.authenticate(token);

		boolean status = authenticate.isAuthenticated();

		if(status)
		{
			return new ResponseEntity<String>("Sucessfully Login",HttpStatus.OK);

		}

		else {

			return new ResponseEntity<String>("Falied",HttpStatus. BAD_REQUEST);		


		}


	}
	@PostMapping("/register")
	public ResponseEntity<String> registercustmer(@RequestBody custmer c )
	{ 
		boolean status = service.savecustmer(c);

		if(status)
		{

			return new ResponseEntity<>("UserSucessfully Registerd" , HttpStatus.CREATED);
		}

		else {

			return  new ResponseEntity<>("Wrong Credentlas" , HttpStatus.BAD_GATEWAY);
		}
		
	}	
	    @GetMapping("/custmer")
	    public ResponseEntity<?> findByid(@RequestParam ("cid") int cid)
	 {
		 custmer c1 = service.findByid(cid);
	
		 {
			if(c1!=null)
			{
				return ResponseEntity.status(HttpStatus.FOUND)
						             .body(c1);
			}
			
			else {
				
				    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
				    		             .body("custmer is Not Found with the iud feteched with the Id: "+cid);
			}
			 
		 }
		 
	 }
}
	     