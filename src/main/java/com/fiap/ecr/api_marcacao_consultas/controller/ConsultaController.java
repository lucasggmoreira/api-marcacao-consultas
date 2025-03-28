package com.fiap.ecr.api_marcacao_consultas.controller;

import com.fiap.ecr.api_marcacao_consultas.model.Consulta;
import com.fiap.ecr.api_marcacao_consultas.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    private final ConsultaService consultaService;
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }
    @PostMapping
    public ResponseEntity criarConsulta(@RequestBody Consulta consulta) {
        try{
            return ResponseEntity.ok(consultaService.salvarConsulta(consulta));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Consulta>> buscarConsulta(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.buscarConsulta(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConsulta(@PathVariable Long id) {
        consultaService.deletarConsulta(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> modificarConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
        consulta.setId(id);
        return ResponseEntity.ok(consultaService.salvarConsulta(consulta));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Consulta>> buscarConsultasPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(consultaService.buscarConsultasPorUsuario(usuarioId));
    }

}
