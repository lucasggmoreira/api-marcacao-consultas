package com.fiap.ecr.api_marcacao_consultas.dto;

import com.fiap.ecr.api_marcacao_consultas.model.TipoUsuario;
import com.fiap.ecr.api_marcacao_consultas.model.Usuario;

public record DadosUsuarioDetalhado(
        Long id,
        String nome,
        String email,
        TipoUsuario tipo
) {

    public DadosUsuarioDetalhado(Usuario dados){
        this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getTipo());
    }
}
