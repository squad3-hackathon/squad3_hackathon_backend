package com.sq3.portifoliosSq3.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserDTO(

        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "Sobrenome é obrigatório")
        String lastname,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "Email inválido")
        String email,

        @NotBlank
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=]).{8,}$", message = "A senha deve conter pelo menos 8 caracteres, 1 letra, 1 número e caractere especial")
        String password) {
}
