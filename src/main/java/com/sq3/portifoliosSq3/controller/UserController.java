package com.sq3.portifoliosSq3.controller;

import com.sq3.portifoliosSq3.exceptions.InvalidLoginException;
import com.sq3.portifoliosSq3.model.DTO.ResponseDTO;
import com.sq3.portifoliosSq3.model.DTO.UserAuthenticationDTO;
import com.sq3.portifoliosSq3.model.DTO.UserDTO;
import com.sq3.portifoliosSq3.service.TokenService;
import com.sq3.portifoliosSq3.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    private TokenService tokenService;
    public UserController(UserService userService) {

        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserAuthenticationDTO userAuth) {
        try {
            String token = userService.authenticateAndGenerateToken(userAuth);
            return ResponseEntity.ok(new ResponseDTO(token));
        } catch (InvalidLoginException exception) {
            Map <String, String> error = new HashMap<>();
            error.put ("Erro", exception.getMessage());
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(error);
        }
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserDTO user) {
        userService.create(user);
        return ResponseEntity.ok().build();


    }
}