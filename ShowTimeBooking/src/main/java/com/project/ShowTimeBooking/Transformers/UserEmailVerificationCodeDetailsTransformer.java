package com.project.ShowTimeBooking.Transformers;

import com.project.ShowTimeBooking.Entities.UserEmailVerificationCodeDetails;
import com.project.ShowTimeBooking.RequestDTO.UserEmailVerificationCodeRequest;

public class UserEmailVerificationCodeDetailsTransformer {
    public static UserEmailVerificationCodeDetails UserEmailVerificationCodeRequestToUserEmailVerificationCode(UserEmailVerificationCodeRequest userEmailVerificationCodeRequest){
        return UserEmailVerificationCodeDetails.builder()
                .emailId(userEmailVerificationCodeRequest.getEmail())
                .verificationCode(userEmailVerificationCodeRequest.getVerificationCode())
                .build();
    }
}
