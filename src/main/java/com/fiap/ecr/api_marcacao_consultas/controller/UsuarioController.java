package com.fiap.ecr.api_marcacao_consultas.controller;

import com.fiap.ecr.api_marcacao_consultas.dto.DadosUsuarioCadastro;
import com.fiap.ecr.api_marcacao_consultas.dto.DadosUsuarioDetalhado;
import com.fiap.ecr.api_marcacao_consultas.dto.DadosUsuarioModificar;
import com.fiap.ecr.api_marcacao_consultas.model.Usuario;
import com.fiap.ecr.api_marcacao_consultas.service.UsuarioService;
import com.fiap.ecr.api_marcacao_consultas.security.JwtTokenProvider;
import com.fiap.ecr.api_marcacao_consultas.dto.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<DadosUsuarioDetalhado> criarUsuario(@RequestBody DadosUsuarioCadastro usuario) {
        return ResponseEntity.ok(new DadosUsuarioDetalhado(usuarioService.salvarUsuario(usuario)));
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

    @GetMapping("/{id}")
    public ResponseEntity<DadosUsuarioDetalhado> retornarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.procurarPorId(id);
        return usuario.map(d -> ResponseEntity.ok(new DadosUsuarioDetalhado(d)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosUsuarioDetalhado> modificarUsuario(@PathVariable Long id, @RequestBody DadosUsuarioModificar usuario) {
        Optional<Usuario> dadosRetorno = usuarioService.modificarUsuario(id, usuario);
        return dadosRetorno.map(d -> ResponseEntity.ok(new DadosUsuarioDetalhado(d))).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removerUsuario(@PathVariable Long id){
        usuarioService.removerUsuario(id);
        return ResponseEntity.noContent().build();
    }





}