package com.project.ShowTimeBooking.Service.impl;

import com.project.ShowTimeBooking.Entities.User;
import com.project.ShowTimeBooking.Entities.UserEmailVerificationCodeDetails;
import com.project.ShowTimeBooking.Enums.Role;
import com.project.ShowTimeBooking.Exceptions.InValidEmailVerificationCodeException;
import com.project.ShowTimeBooking.Exceptions.PasswordReTypePasswordNotMatchException;
import com.project.ShowTimeBooking.Exceptions.UserAlreadyPresentException;
import com.project.ShowTimeBooking.Exceptions.UserNotFoundException;
import com.project.ShowTimeBooking.Generators.EmailGenerator;
import com.project.ShowTimeBooking.Generators.UserCodeGenerator;
import com.project.ShowTimeBooking.Repository.UserEmailVerificationCodeDetailsRepository;
import com.project.ShowTimeBooking.Repository.UserRepository;
import com.project.ShowTimeBooking.RequestDTO.UserEmailRequest;
import com.project.ShowTimeBooking.RequestDTO.UserEmailVerificationCodeRequest;
import com.project.ShowTimeBooking.RequestDTO.UserRequest;
import com.project.ShowTimeBooking.Service.MailConfigurationService;
import com.project.ShowTimeBooking.Service.UserEmailVerificationCodeDetailsService;
import com.project.ShowTimeBooking.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.project.ShowTimeBooking.Service.impl.MailConfigurationServiceImpl.SENDER_EMAIL;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCodeGenerator userCodeGenerator;
    @Autowired
    private EmailGenerator emailGenerator;
    @Autowired
    private MailConfigurationService mailConfigurationService;
    @Autowired
    private UserEmailVerificationCodeDetailsService userEmailVerificationCodeDetailsService;
    @Autowired
    private UserEmailVerificationCodeDetailsRepository userEmailVerificationCodeDetailsRepository;
    @Autowired
    private PasswordManagerServiceImpl passwordManagerService;

    //Method 1: add Details
    public String addUser(UserRequest userRequest)throws Exception{
        Optional<User> optionalUser=userRepository.findByEmailId(userRequest.getEmailId());
        if(optionalUser.isPresent()){
            throw new UserAlreadyPresentException("User already Registered");
        }
        //validating the match of password and retype password
        if(!userRequest.getPassword().equals(userRequest.getReTypePassword())){
            throw new PasswordReTypePasswordNotMatchException("Password and reType password did not match");
        }

        //validating the email of the user
        String codeFromUser=userRequest.getEmailVerificationCode();
        mailValidation(userRequest.getEmailId(), codeFromUser);

        User user= UserTransformer.userRequestToUser(userRequest);
        String code=userCodeGenerator.generate("USR");

        //setting the attributes for user
        user.setCode(code);
        user.setRole(Role.USER);

        User savedUser=userRepository.save(user);

        //sending registration confirmation mail to the user
        String emailBody=emailGenerator.userSuccessfulRegistrationMessageEmailGenerator(user.getName());
        mailConfigurationService.mailSender(SENDER_EMAIL,user.getEmailId(), emailBody, "User Registration Confirmation");

        return "User "+savedUser.getName()+" has been registered successfully";
    }
    public String sendEmailValidationCode(UserEmailRequest userEmailRequest)throws Exception{
        //for encoding the verification code
        String email=userEmailRequest.getEmail();
        String code=emailGenerator.userEmailValidationCodeGenerator();
        Optional<UserEmailVerificationCodeDetails> optionalUserEmailVerificationCode=userEmailVerificationCodeDetailsService.findUserEmailVerificationCodeByEmailId(email);

        if(optionalUserEmailVerificationCode.isPresent()){
            UserEmailVerificationCodeDetails userEmailVerificationCodeDetails =optionalUserEmailVerificationCode.get();
            userEmailVerificationCodeDetails.setVerificationCode(passwordManagerService.encode(code));
            userEmailVerificationCodeDetailsRepository.save(userEmailVerificationCodeDetails);
        }else{
            userEmailVerificationCodeDetailsService.addUserEmailVerificationCode(new UserEmailVerificationCodeRequest(email, passwordManagerService.encode(code)));
        }
        mailConfigurationService.mailSender("applicationtesting1604@gmail.com",email, code, "Email Validation Code");
        return "Verification code sent successfully to the mail"+email;
    }
    //below methods are used for internal purposes...not for api calling
    private boolean mailValidation(String email, String code)throws Exception{

        Optional<UserEmailVerificationCodeDetails> optionalUserEmailVerificationCode=userEmailVerificationCodeDetailsService.findUserEmailVerificationCodeByEmailId(email);
        if(optionalUserEmailVerificationCode.isPresent()){
            String userEmailCode=optionalUserEmailVerificationCode.get().getVerificationCode();
            if(passwordManagerService.matches(code,userEmailCode))return true;
            else throw  new InValidEmailVerificationCodeException("Invalid Code");
        }else{
            throw  new InValidEmailVerificationCodeException("Invalid Code!!!");
        }
    }
    public User getUserByEmailId(String emailId)throws Exception{
        Optional<User>optionalUser=userRepository.findByEmailId(emailId);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("Invalid Credentials");
        }
        return optionalUser.get();
    }
}
