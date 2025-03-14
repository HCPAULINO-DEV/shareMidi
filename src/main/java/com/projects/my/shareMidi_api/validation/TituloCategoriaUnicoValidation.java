package com.projects.my.shareMidi_api.validation;

import com.projects.my.shareMidi_api.dto.CriarCategoriaDto;
import com.projects.my.shareMidi_api.repository.CategoriaRepository;
import org.springframework.stereotype.Component;

@Component
public class TituloCategoriaUnicoValidation implements CategoriaValidation{

    private final CategoriaRepository categoriaRepository;

    public TituloCategoriaUnicoValidation(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void validar(CriarCategoriaDto dto) {
        if (categoriaRepository.existsByTitulo(dto.titulo())){
            throw new RuntimeException("Categoria já existente");

        }

    }

}
