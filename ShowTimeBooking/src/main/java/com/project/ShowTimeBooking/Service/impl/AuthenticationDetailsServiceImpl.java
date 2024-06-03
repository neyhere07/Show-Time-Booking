package com.project.ShowTimeBooking.Service.impl;

import com.project.ShowTimeBooking.Service.AuthenticationDetailsService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationDetailsServiceImpl implements AuthenticationDetailsService {
    public String getAuthenticationDetails(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails= (UserDetails)authentication.getPrincipal();
        String userEmailId= userDetails.getUsername();
        return userEmailId;
    }
}
