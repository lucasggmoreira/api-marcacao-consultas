package com.fiap.ecr.api_marcacao_consultas.dto;

import com.fiap.ecr.api_marcacao_consultas.model.TipoUsuario;

public record DadosUsuarioModificar(
        String nome,
        String email,
        String senha,
        TipoUsuario tipo
) {
}
