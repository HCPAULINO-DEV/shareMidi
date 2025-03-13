package com.projects.my.shareMidi_api.dto;

import jakarta.validation.constraints.NotBlank;

public record CriarCategoriaDto(
        @NotBlank(message = "Título obrigatório")
        String titulo,

        @NotBlank(message = "Cor obrigatório")
        String cor
) {
}
