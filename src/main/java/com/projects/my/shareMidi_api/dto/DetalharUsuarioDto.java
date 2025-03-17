package com.projects.my.shareMidi_api.dto;

import com.projects.my.shareMidi_api.model.Usuario;

public record DetalharUsuarioDto(
        Long id,
        String username,
        String password
) {
    public DetalharUsuarioDto(Usuario usuario){
        this(usuario.getId(), usuario.getUsername(), usuario.getPassword());
    }
}
