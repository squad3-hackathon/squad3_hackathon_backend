package com.sq3.portifoliosSq3.controller;

import com.sq3.portifoliosSq3.model.DTO.ResponseDTO;
import com.sq3.portifoliosSq3.model.DTO.UserAuthenticationDTO;
import com.sq3.portifoliosSq3.model.DTO.UserDTO;
import com.sq3.portifoliosSq3.model.User;
import com.sq3.portifoliosSq3.model.converter.UserConverter;
import com.sq3.portifoliosSq3.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private final UserConverter userConverter;

    @Autowired
    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserAuthenticationDTO userAuth) {
        String token = userService.authenticateAndGenerateToken(userAuth);
        return ResponseEntity.ok(new ResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO userDTO) {
        User user = this.userConverter.convertDtoToEntity(userDTO);
        User userCreated = this.userService.create(user);
        return ResponseEntity.ok(userCreated);
    }

}
