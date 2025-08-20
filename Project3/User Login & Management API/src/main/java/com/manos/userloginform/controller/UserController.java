package com.manos.userloginform.controller;


import com.manos.userloginform.model.User;
import com.manos.userloginform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable (value="id") Long userId){
       Optional<User> user=userService.getUserById(userId);
        if(user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User>  addUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addUser(user),HttpStatus.CREATED);
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,
                                            @PathVariable (value="id") Long userId){
    User updatedUser = userService.updateUser(user, userId);
    if (updatedUser != null) {
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
            }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser( @PathVariable (value="id") Long userId){
          boolean deleted=userService.deleteUser(userId);

          if(deleted){
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } else {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
    }


     }





