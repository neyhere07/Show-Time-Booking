package com.project.ShowTimeBooking.Config;

import com.project.ShowTimeBooking.Entities.User;
import com.project.ShowTimeBooking.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetails {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userEmailId) throws UsernameNotFoundException {
        Optional<User> optionalUser=userRepository.findByEmailId(userEmailId);
        if(!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("Invalid Username or password");
        }
        User user=optionalUser.get();
        return new UserDetailsCreator(user);
    }
}
