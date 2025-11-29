package com.data_jpa.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    // what is happening behind the scene
    // the data-jpa is a wrapper class of jpa
    // orm like hibernate implements the jpa interface
    // jpa is also a specificatioon for class
    // like if we marks a class @Entity .....hibernate maps the class to table in database

    // so if we save the user with .save() method ...what is happening
    // creating the session
    // begin the transaction
    // saving the user
    // commit the transaction
    // end the session
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserClassSpringBoot> createUser(@RequestBody UserClassSpringBoot user) {
        UserClassSpringBoot saved = userService.createUser(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserClassSpringBoot>> getAllUsers(){
        List<UserClassSpringBoot> users = userService.getAllUsers();

        return new ResponseEntity<>( users , HttpStatus.OK );
    }



}
