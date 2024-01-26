package com.sq3.portifoliosSq3.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserAuthenticationDTO (

        @NotNull
        @NotBlank
        @Email(message = "Email inválido")
        String email,

        @NotNull
        @NotBlank
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=]).{8,}$", message = "A senha deve conter pelo menos 8 caracteres, 1 letra, 1 número e 1 caractere especial")
        String password) {
}
