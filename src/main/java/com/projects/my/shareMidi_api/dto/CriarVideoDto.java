package com.projects.my.shareMidi_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CriarVideoDto(
        @NotBlank(message = "Título obrigatório")
        String titulo,

        @NotBlank(message = "Descrição obrigatório")
        String descricao,

        @NotBlank(message = "URL obrigatório")
        String url,

        @JsonProperty(defaultValue = "1")
        Long categoria
) {
}
