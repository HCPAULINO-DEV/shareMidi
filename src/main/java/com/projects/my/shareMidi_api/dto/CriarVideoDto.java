package com.projects.my.shareMidi_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarVideoDto(
        @NotBlank(message = "Título obrigatório")
        String titulo,

        @NotBlank(message = "Descrição obrigatório")
        String descricao,

        @NotBlank(message = "URL obrigatório")
        String url,

        @NotNull(message = "Categoria obrigatória")
        Long categoria
) {
}
