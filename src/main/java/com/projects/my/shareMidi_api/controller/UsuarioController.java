package com.projects.my.shareMidi_api.controller;

import com.projects.my.shareMidi_api.dto.CriarUsuarioDto;
import com.projects.my.shareMidi_api.dto.DetalharUsuarioDto;
import com.projects.my.shareMidi_api.repository.UsuarioRepository;
import com.projects.my.shareMidi_api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<DetalharUsuarioDto> criarUsuario(@RequestBody @Valid CriarUsuarioDto dto, UriComponentsBuilder uriComponentsBuilder){
        var usuario = usuarioService.criarUsuario(dto);
        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalharUsuarioDto(usuario));

    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);

        return ResponseEntity.noContent().build();
    }
}
