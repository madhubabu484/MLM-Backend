package com.SpringSecurity.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.SpringSecurity.Entity.Custmer;
import com.SpringSecurity.Repository.custmerrepo;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService
{
	 @Autowired
	    private custmerrepo customerrepo;

	    @Override
	    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
	        // Load user info from OAuth2 provider
	        OAuth2User oauth2User = super.loadUser(userRequest);
	        String registrationId = userRequest.getClientRegistration().getRegistrationId();

	        String email = null;
	        String name  = null;

	        // Extract user details based on provider
	        if ("google".equalsIgnoreCase(registrationId)) {
	            email = oauth2User.getAttribute("email");
	            name  = oauth2User.getAttribute("name");

	        } else if ("facebook".equalsIgnoreCase(registrationId)) {
	        	email=oauth2User.getAttribute("email");
	            name = oauth2User.getAttribute("name");
	        

	        } else {
	            throw new OAuth2AuthenticationException("Login with " + registrationId + " is not supported");
	        }

	        Custmer user = customerrepo.findByEmail(email);

	        if (user == null) {
	            user = new Custmer();
	            user.setEmail(email);
	            user.setName(name);
	            user.setPassword("OAUTH_USER"); 
	            user = customerrepo.save(user);
	           
	        }
	        return oauth2User;
	    }

}
