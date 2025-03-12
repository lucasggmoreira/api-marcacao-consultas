package com.fiap.ecr.api_marcacao_consultas.dto;

import com.fiap.ecr.api_marcacao_consultas.model.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record DadosUsuarioCadastro(
        @NotNull
        String nome,
        @NotNull
        @Email
        String email,
        @Length(min = 8, message = "Senha deve ter no mínimo 8 caracteres.")
        String senha,
        TipoUsuario tipo
) {
}
