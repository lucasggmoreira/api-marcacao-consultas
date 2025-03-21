package com.fiap.ecr.api_marcacao_consultas.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DisponibilidadeDTO(
        @NotNull
        Long id,
        @NotNull
        LocalDateTime dataHora
) {
}
