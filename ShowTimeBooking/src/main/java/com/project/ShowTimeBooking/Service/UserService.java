package com.project.ShowTimeBooking.Service;

import com.project.ShowTimeBooking.Entities.User;
import com.project.ShowTimeBooking.RequestDTO.UserEmailRequest;
import com.project.ShowTimeBooking.RequestDTO.UserRequest;

public interface UserService {
    public String addUser(UserRequest userRequest)throws Exception;
    public String sendEmailValidationCode(UserEmailRequest userEmailRequest)throws Exception;
    private boolean mailValidation(String email, String code)throws Exception{
        return true;
    }
    public User getUserByEmailId(String emailId)throws Exception;
}
