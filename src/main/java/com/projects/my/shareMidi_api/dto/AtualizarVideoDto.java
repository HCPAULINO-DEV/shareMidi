package com.projects.my.shareMidi_api.dto;

import jakarta.validation.constraints.NotBlank;

public record AtualizarVideoDto(
        @NotBlank(message = "Título obrigatório")
        String titulo,

        @NotBlank(message = "Descrição obrigatório")
        String descricao,

        @NotBlank(message = "URL obrigatório")
        String url
) {
}
