package com.sq3.portifoliosSq3.service;

import com.sq3.portifoliosSq3.exceptions.InvalidLoginException;
import com.sq3.portifoliosSq3.exceptions.UserCreateException;
import com.sq3.portifoliosSq3.model.DTO.UserAuthenticationDTO;
import com.sq3.portifoliosSq3.model.DTO.UserDTO;
import com.sq3.portifoliosSq3.model.User;
import com.sq3.portifoliosSq3.repository.UserRepository;
import com.sq3.portifoliosSq3.security.CustomUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private TokenService tokenService;
    private AuthenticationManager authenticationManager;
    private AuthenticatedUserService authenticatedUserService;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, AuthenticationManager authenticationManager, AuthenticatedUserService authenticatedUserService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.authenticatedUserService = authenticatedUserService;
    }

    public Optional<User> getUserById(Long id) { return userRepository.findById(id); }

    public User create(UserDTO userDTO) {
        Optional<User> existUser = userRepository.findByEmail(userDTO.email());

        if (existUser.isPresent()) {
            throw new UserCreateException("Usuário já cadastrado " + userDTO.email());
        }

        String encryptedPassword = passwordEncoder.encode(userDTO.password());
        User user = new User(userDTO.name(), userDTO.lastName(), userDTO.email(), encryptedPassword);

        return userRepository.save(user);
    }

    public String authenticateAndGenerateToken(UserAuthenticationDTO userAuth) throws InvalidLoginException {

        Optional<User> userOptional= userRepository.findByEmail(userAuth.email());
        if(userOptional.isEmpty()) {
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
    public Optional<User> getUserByUserName(String userName) { return userRepository.findByEmail(userName); }
    public Long getAuthenticatedUserId() {
        return authenticatedUserService.getAuthenticatedUserId();
    }
}