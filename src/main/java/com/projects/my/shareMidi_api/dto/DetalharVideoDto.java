package com.projects.my.shareMidi_api.dto;

import com.projects.my.shareMidi_api.model.Video;

public record DetalharVideoDto(
        Long id,
        String titulo,
        String descricao,
        String url,
        String categoria
) {
    public DetalharVideoDto(Video video){
        this(video.getId(), video.getTitulo(), video.getDescricao(), video.getUrl(), video.getCategoria().getTitulo());
    }
}
