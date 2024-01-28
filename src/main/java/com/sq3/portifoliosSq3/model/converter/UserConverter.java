package com.sq3.portifoliosSq3.model.converter;

import com.sq3.portifoliosSq3.model.DTO.UserDTO;
import com.sq3.portifoliosSq3.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    /**
     * Metodo utilizado para cadastro do usuario
     */
    public User convertDtoToEntity(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.name())
                .lastname(userDTO.lastname())
                .email(userDTO.email())
                .password(userDTO.password())
                .build();
    }

}
