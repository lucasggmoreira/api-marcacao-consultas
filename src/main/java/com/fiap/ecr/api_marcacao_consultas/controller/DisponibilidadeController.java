package com.fiap.ecr.api_marcacao_consultas.controller;

import com.fiap.ecr.api_marcacao_consultas.dto.DisponibilidadeDTO;
import com.fiap.ecr.api_marcacao_consultas.service.DisponibilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/disponibilidade")
public class DisponibilidadeController {

    @Autowired
    private DisponibilidadeService disponibilidadeService;

    @PostMapping
    public ResponseEntity<DisponibilidadeDTO> cadastrarHorario(@RequestBody DisponibilidadeDTO disponibilidadeDTO) {
        DisponibilidadeDTO horarioCadastrado = disponibilidadeService.cadastrarHorario(disponibilidadeDTO);
        return ResponseEntity.ok(horarioCadastrado);
    }

    @GetMapping("/medico/{id}")
    public ResponseEntity<List<DisponibilidadeDTO>> listarHorariosPorMedico(@PathVariable Long idMedico) {
        List<DisponibilidadeDTO> horariosDisponiveis = disponibilidadeService.listarHorariosPorMedico(idMedico);
        return ResponseEntity.ok(horariosDisponiveis);
    }


}
