package com.AbdulKhaliq.EcommerceApplication.services;

import com.AbdulKhaliq.EcommerceApplication.entities.User;

import java.util.List;

public interface UserService
{
   User CreateUser(User user);
   User CreateAdmin(User user);
   List<User> getAllUser();
}
