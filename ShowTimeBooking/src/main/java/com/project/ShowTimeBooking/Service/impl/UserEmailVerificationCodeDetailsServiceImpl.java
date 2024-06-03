package com.project.ShowTimeBooking.Service.impl;

import com.project.ShowTimeBooking.Entities.UserEmailVerificationCodeDetails;
import com.project.ShowTimeBooking.Repository.UserEmailVerificationCodeDetailsRepository;
import com.project.ShowTimeBooking.RequestDTO.UserEmailVerificationCodeRequest;
import com.project.ShowTimeBooking.Service.UserEmailVerificationCodeDetailsService;
import com.project.ShowTimeBooking.Transformers.UserEmailVerificationCodeDetailsTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEmailVerificationCodeDetailsServiceImpl implements UserEmailVerificationCodeDetailsService {
    @Autowired
    private UserEmailVerificationCodeDetailsRepository userEmailVerificationCodeRepository;

    public Optional<UserEmailVerificationCodeDetails> findUserEmailVerificationCodeByEmailId(String email){
        Optional<UserEmailVerificationCodeDetails>userEmailVerificationCode=userEmailVerificationCodeRepository.findByEmailId(email);
        return userEmailVerificationCode;
    }
    public void addUserEmailVerificationCode(UserEmailVerificationCodeRequest userEmailVerificationCodeRequest)throws Exception{
        UserEmailVerificationCodeDetails userEmailVerificationCode= UserEmailVerificationCodeDetailsTransformer.UserEmailVerificationCodeRequestToUserEmailVerificationCode(userEmailVerificationCodeRequest);
        userEmailVerificationCodeRepository.save(userEmailVerificationCode);
    }
}
