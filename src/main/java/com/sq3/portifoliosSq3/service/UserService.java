package com.sq3.portifoliosSq3.service;

import com.sq3.portifoliosSq3.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }
}
