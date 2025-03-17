package com.projects.my.shareMidi_api.service;

import com.projects.my.shareMidi_api.dto.CriarUsuarioDto;
import com.projects.my.shareMidi_api.model.Usuario;
import com.projects.my.shareMidi_api.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario criarUsuario(CriarUsuarioDto dto){
        Usuario usuario = new Usuario(dto, passwordEncoder);
        usuarioRepository.save(usuario);

        return usuario;

    }

    public void deletarUsuario(Long id){
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não foi encontrado com ID: " + id));

        usuarioRepository.delete(usuario);

    }
}
