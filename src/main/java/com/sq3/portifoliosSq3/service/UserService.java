package com.sq3.portifoliosSq3.service;

import com.sq3.portifoliosSq3.exceptions.UserCreateException;
import com.sq3.portifoliosSq3.model.DTO.UserDTO;
import com.sq3.portifoliosSq3.model.User;
import com.sq3.portifoliosSq3.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User create(UserDTO userDTO) {
        User existUser = userRepository.findByEmail(userDTO.email());

        if (existUser != null) {
            throw new UserCreateException("Usuário já cadastrado " + existUser.getEmail());
        }

        String encryptedPassword = passwordEncoder.encode(userDTO.password());
        User user = new User(userDTO.name(), userDTO.lastname(), userDTO.email(), encryptedPassword);

        return userRepository.save(user);


    }
}