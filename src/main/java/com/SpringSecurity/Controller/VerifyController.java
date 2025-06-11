package com.SpringSecurity.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringSecurity.PasswordDTO.DTORequest;
import com.SpringSecurity.Service.VerifyPassword;

@RestController
public class VerifyController {
	
	@Autowired
	private VerifyPassword password;
	
	@PostMapping("/verifypassword")
	public ResponseEntity<String> passwordverify(@RequestBody DTORequest request)
	{
		
              boolean match = password.verifypassword(request.getEmail(), request.getPassword());
            		  {
            	             if(match)
            	             {
            	            	 return ResponseEntity.ok("Password Verified Sucessfully");
            	            			 
            	             }
            	                 return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            	                                      .body("InvalidPassword");
            	             
            		  }
 	}

}
