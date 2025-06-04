package com.SpringSecurity.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.SpringSecurity.Entity.Customer;
import com.SpringSecurity.Repository.customerrepo;


@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private customerrepo customerrepo;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        
                  String email =null;
                  String name =null;
       

        if ("google".equalsIgnoreCase(registrationId)) {
            email = oauth2User.getAttribute("email");
            name  = oauth2User.getAttribute("name");
            
   
        } else if ("facebook".equalsIgnoreCase(registrationId)) {

            name = oauth2User.getAttribute("name");
           
        } else {
            throw new OAuth2AuthenticationException("Login with " + registrationId + " is not supported");
        }

                     final String finalEmail = email;
                     final String finalName = name;

                     Customer user = customerrepo.findByEmail(email);

                     if (user == null) {
                         user = new Customer();
                         user.setEmail(finalEmail);
                         user.setName(finalName);
                         user = customerrepo.save(user);
                     }

                          
    

        return oauth2User;
    }
}


