package com.projects.my.shareMidi_api.dto;

import com.projects.my.shareMidi_api.model.Categoria;

public record DetalharCategoriaDto(
        Long id,
        String titulo,
        String cor
) {
    public DetalharCategoriaDto(Categoria categoria){
        this(categoria.getId(), categoria.getTitulo(), categoria.getCor());
    }
}
