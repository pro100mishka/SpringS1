package com.geekbrains.spring.market.services.user;


import com.geekbrains.spring.market.entity.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
}
