package com.fiap.ecr.api_marcacao_consultas.repository;

import com.fiap.ecr.api_marcacao_consultas.model.Disponibilidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {
    List<Disponibilidade> findByMedicoId(Long medicoId);
}
