package com.AbdulKhaliq.EcommerceApplication.services.servicesImpl;
import com.AbdulKhaliq.EcommerceApplication.entities.User;
import com.AbdulKhaliq.EcommerceApplication.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService
{

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepo.findByEmail(username).orElseThrow(() -> new RuntimeException("user Not Found with email :" + username));
        return user;
    }
}
