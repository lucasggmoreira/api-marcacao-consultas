package com.fiap.ecr.api_marcacao_consultas.service;

import com.fiap.ecr.api_marcacao_consultas.dto.DisponibilidadeDTO;
import com.fiap.ecr.api_marcacao_consultas.model.Disponibilidade;
import com.fiap.ecr.api_marcacao_consultas.model.TipoUsuario;
import com.fiap.ecr.api_marcacao_consultas.model.Usuario;
import com.fiap.ecr.api_marcacao_consultas.repository.DisponibilidadeRepository;
import com.fiap.ecr.api_marcacao_consultas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisponibilidadeService {

    @Autowired
    private DisponibilidadeRepository disponibilidadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DisponibilidadeDTO cadastrarHorario(DisponibilidadeDTO disponibilidadeDTO) {
        Usuario medico = usuarioRepository.findById(disponibilidadeDTO.id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (medico.getTipo() != TipoUsuario.MEDICO) {
            throw new RuntimeException("O usuário deve ser um médico");
        }

        Disponibilidade disponibilidade = new Disponibilidade();
        disponibilidade.setMedico(medico);
        disponibilidade.setDataHora(disponibilidadeDTO.dataHora());

        disponibilidadeRepository.save(disponibilidade);

        return disponibilidadeDTO;
    }
}
