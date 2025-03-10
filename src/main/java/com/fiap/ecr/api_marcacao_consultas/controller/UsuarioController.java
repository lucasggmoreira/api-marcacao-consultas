package com.fiap.ecr.api_marcacao_consultas.controller;

import com.fiap.ecr.api_marcacao_consultas.dto.DadosUsuarioCriado;
import com.fiap.ecr.api_marcacao_consultas.model.Usuario;
import com.fiap.ecr.api_marcacao_consultas.service.UsuarioService;
import com.fiap.ecr.api_marcacao_consultas.security.JwtTokenProvider;
import com.fiap.ecr.api_marcacao_consultas.dto.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final JwtTokenProvider jwtTokenProvider;

    public UsuarioController(UsuarioService usuarioService, JwtTokenProvider jwtTokenProvider) {
        this.usuarioService = usuarioService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping
    public ResponseEntity<DadosUsuarioCriado> criarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(new DadosUsuarioCriado(usuarioService.salvarUsuario(usuario)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Usuario usuario = usuarioService.autenticar(loginRequest.email(), loginRequest.senha());
            String token = jwtTokenProvider.gerarToken(usuario);
            return ResponseEntity.ok().body(Map.of("token", token));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }


}