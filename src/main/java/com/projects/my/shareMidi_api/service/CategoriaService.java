package com.projects.my.shareMidi_api.service;

import com.projects.my.shareMidi_api.dto.DetalharCategoriaDto;
import com.projects.my.shareMidi_api.model.Categoria;
import com.projects.my.shareMidi_api.model.Video;
import com.projects.my.shareMidi_api.repository.CategoriaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Page<DetalharCategoriaDto> exibirCategorias(Pageable pageable) {
        Page<Categoria> videos = categoriaRepository.findAll(pageable);

        if (videos.isEmpty()){
            throw new RuntimeException("Nenhuma categoria foi encontrada");
        }

        Page<DetalharCategoriaDto> pageDto = videos.map(DetalharCategoriaDto::new);

        return pageDto;

    }

    public Categoria exibirUnicaCategoria(Long id) {
        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi encontrada categoria com ID: " + id));

        return categoria;

    }

}
