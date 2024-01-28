package com.sq3.portifoliosSq3.service;

import com.sq3.portifoliosSq3.exceptions.models.InvalidLoginException;
import com.sq3.portifoliosSq3.exceptions.models.UserCreateException;
import com.sq3.portifoliosSq3.model.DTO.UserAuthenticationDTO;
import com.sq3.portifoliosSq3.model.User;
import com.sq3.portifoliosSq3.model.enums.Role;
import com.sq3.portifoliosSq3.repository.UserRepository;
import com.sq3.portifoliosSq3.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {


    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public User create(User user) {
        if (this.userRepository.existsByEmail(user.getEmail())) {
            throw new UserCreateException("Usuário já cadastrado " + user.getEmail());
        }
        String encryptedPassword = this.passwordEncoder.encode(user.getPassword());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);

        user.setPassword(encryptedPassword);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public String authenticateAndGenerateToken(UserAuthenticationDTO userAuth) throws InvalidLoginException {

        Optional<User> userOptional = userRepository.findByEmail(userAuth.email());
        if (userOptional.isEmpty()) {
            throw new InvalidLoginException("Usuário não encontrado");
        }

        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(userAuth.email(), userAuth.password());
            var auth = authenticationManager.authenticate(usernamePassword);

            CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
            User user = customUserDetails.getUser();

            return tokenService.generateToken(user);
        } catch (BadCredentialsException exception) {
            throw new InvalidLoginException("Senha inválida!");
        }
    }

}