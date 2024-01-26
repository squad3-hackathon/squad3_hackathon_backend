package com.sq3.portifoliosSq3.controller;

import com.sq3.portifoliosSq3.model.DTO.ResponseDTO;
import com.sq3.portifoliosSq3.model.DTO.UserAuthenticationDTO;
import com.sq3.portifoliosSq3.model.DTO.UserDTO;
import com.sq3.portifoliosSq3.model.User;
import com.sq3.portifoliosSq3.security.CustomUserDetails;
import com.sq3.portifoliosSq3.security.TokenService;
import com.sq3.portifoliosSq3.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private AuthenticationManager authenticationManager;
    private UserService userService;

    private TokenService tokenService;
    public UserController(UserService userService, AuthenticationManager authenticationManager, TokenService tokenService) {

        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserAuthenticationDTO userAuth) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(userAuth.email(), userAuth.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();

        User user = customUserDetails.getUser();

        var token = tokenService.generateToken(user);


        return ResponseEntity.ok(new ResponseDTO(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserDTO user) {
        userService.create(user);
        return ResponseEntity.ok().build();


    }
}
