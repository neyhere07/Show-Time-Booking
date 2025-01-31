package com.project.ShowTimeBooking.Controller;

import com.project.ShowTimeBooking.RequestDTO.UserEmailRequest;
import com.project.ShowTimeBooking.RequestDTO.UserRequest;
import com.project.ShowTimeBooking.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")

public class UserController {
    @Autowired
    private UserService userService;

    //Method 1:
    @PostMapping("/email-authentication-code-to-user-email")
    public ResponseEntity sendEmailValidationCode(@RequestBody UserEmailRequest userEmailRequest){
        try {
            return new ResponseEntity<>(userService.sendEmailValidationCode(userEmailRequest), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody UserRequest userRequest){
        try {
            return new ResponseEntity<>(userService.addUser(userRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
