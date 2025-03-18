package com.projects.my.shareMidi_api.validation;

import com.projects.my.shareMidi_api.dto.AtualizarCategoriaDto;
import com.projects.my.shareMidi_api.dto.CriarCategoriaDto;
import com.projects.my.shareMidi_api.exception.CategoriaJaExistenteException;
import com.projects.my.shareMidi_api.repository.CategoriaRepository;
import org.springframework.stereotype.Component;

@Component
public class TituloCategoriaUnicoValidation implements CategoriaValidation{

    private final CategoriaRepository categoriaRepository;

    public TituloCategoriaUnicoValidation(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void validar(Object object) {
        if (object instanceof CriarCategoriaDto criarDto) {
            if (categoriaRepository.existsByTitulo(criarDto.titulo())) {
                throw new CategoriaJaExistenteException("Categoria já existente");
            }
        } else if (object instanceof AtualizarCategoriaDto atualizarDto) {
            if (categoriaRepository.existsByTitulo(atualizarDto.titulo())) {
                throw new CategoriaJaExistenteException("Categoria já existente");
            }
        } else {
            throw new IllegalArgumentException("Tipo de DTO não suportado.");
        }
    }

}
