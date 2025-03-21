package com.fiap.ecr.api_marcacao_consultas.service;

import com.fiap.ecr.api_marcacao_consultas.dto.DadosUsuarioCadastro;
import com.fiap.ecr.api_marcacao_consultas.dto.DadosUsuarioModificar;
import com.fiap.ecr.api_marcacao_consultas.model.Usuario;
import com.fiap.ecr.api_marcacao_consultas.repository.UsuarioRepository;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final ResourcePatternResolver resourcePatternResolver;

    public UsuarioService(UsuarioRepository usuarioRepository, ResourcePatternResolver resourcePatternResolver) {
        this.usuarioRepository = usuarioRepository;
        this.resourcePatternResolver = resourcePatternResolver;
    }
    public Usuario salvarUsuario(DadosUsuarioCadastro dados) {
        var usuario = new Usuario(dados);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }

        return usuario;
    }

    public Optional<Usuario> procurarPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> modificarUsuario(Long id, DadosUsuarioModificar dados) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            if (!(dados.tipo() == null)) usuario.get().setTipo(dados.tipo());
            if (!(dados.nome() == null)) usuario.get().setNome(dados.nome());
            if (!(dados.email() == null)) usuario.get().setEmail(dados.email());
            if (!(dados.senha() == null)) usuario.get().setSenha(passwordEncoder.encode(dados.senha()));
            return Optional.of(usuarioRepository.save(usuario.get()));
        } else {
            return Optional.empty();
        }
    }

    public void removerUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
