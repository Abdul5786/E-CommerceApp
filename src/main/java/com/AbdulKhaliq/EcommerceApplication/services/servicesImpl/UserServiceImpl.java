package com.AbdulKhaliq.EcommerceApplication.services.servicesImpl;
import com.AbdulKhaliq.EcommerceApplication.entities.User;
import com.AbdulKhaliq.EcommerceApplication.enums.Roles;
import com.AbdulKhaliq.EcommerceApplication.exception.ResponseMessageException;
import com.AbdulKhaliq.EcommerceApplication.repositories.UserRepo;
import com.AbdulKhaliq.EcommerceApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl  implements UserService
{
    @Autowired
    private UserRepo userRepo;

     @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User CreateUser(User user)
    {
        User found = userRepo.findByEmail(user.getEmail()).orElseThrow();
        if (found!=null)
        {
            throw new ResponseMessageException(" user with email id : " + user.getEmail() +  "    already exist");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Roles.User);
        User savedUser = userRepo.save(user);
        return   savedUser;

    }

    @Override
    public User CreateAdmin(User user)
    {
        User found = userRepo.findByEmail(user.getEmail()).orElseThrow();
        if (found!=null)
        {
            throw new ResponseMessageException(" user with email id : " + user.getEmail() +  "    already exist");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Roles.Admin);
        User savedUser = userRepo.save(user);
        return   savedUser;
    }

    @Override
    public List<User> getAllUser()
    {
           return userRepo.findAll();
    }
}
