package com.project.ShowTimeBooking.Transformers;

import com.project.ShowTimeBooking.Entities.User;
import com.project.ShowTimeBooking.RequestDTO.UserRequest;

import java.util.ArrayList;

public class UserTransformer {
    public static User userRequestToUser(UserRequest userRequest){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return User.builder()
                .name(userRequest.getName())
                .age(userRequest.getAge())
                .emailId(userRequest.getEmailId())
                .contactNumber(userRequest.getContactNumber())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .ticketList(new ArrayList<>())
                .build();
    }
}
