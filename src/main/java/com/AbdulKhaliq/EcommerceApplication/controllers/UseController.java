package com.AbdulKhaliq.EcommerceApplication.controllers;


import com.AbdulKhaliq.EcommerceApplication.entities.User;
import com.AbdulKhaliq.EcommerceApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UseController
{
    @Autowired
    private UserService userService;

     @PostMapping(value = "/create-user")
    public ResponseEntity<User> createUser(User user)
    {
        User created = userService.CreateUser(user);
        return ResponseEntity.ok(created);
    }

   @PostMapping(value = "/createAdmin")
   public ResponseEntity<User> createAdmin(User user)
   {
       User createAdmin = userService.CreateAdmin(user);
       return ResponseEntity.ok(createAdmin);
   }

    @GetMapping(value = "getAllUsers")
    public ResponseEntity<List<User>> getAllUser()
    {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
