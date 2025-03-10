package com.fiap.ecr.api_marcacao_consultas.dto;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

public record LoginRequest(
        @Email(message = "Email inválido")
        String email,
        @Length(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
        String senha
) {

}
