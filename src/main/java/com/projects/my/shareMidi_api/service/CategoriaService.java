package com.projects.my.shareMidi_api.service;

import com.projects.my.shareMidi_api.dto.AtualizarCategoriaDto;
import com.projects.my.shareMidi_api.dto.CriarCategoriaDto;
import com.projects.my.shareMidi_api.dto.DetalharCategoriaDto;
import com.projects.my.shareMidi_api.model.Categoria;
import com.projects.my.shareMidi_api.model.Video;
import com.projects.my.shareMidi_api.repository.CategoriaRepository;
import jakarta.validation.Valid;
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
        return categoriaRepository.findAll(pageable)
                .map(DetalharCategoriaDto::new);

    }

    public Categoria exibirUnicaCategoria(Long id) {
        var categoria = buscarCategoria(id);

        return categoria;

    }

    public Categoria criarCategoria(CriarCategoriaDto dto) {
        Categoria categoria = new Categoria(dto);
        categoriaRepository.save(categoria);

        return categoria;

    }

    public Categoria atualizarCategoria(Long id, AtualizarCategoriaDto dto){
        var categoria = buscarCategoria(id);
        categoria.atualizar(dto);
        categoriaRepository.save(categoria);

        return categoria;

    }

    public void deletarCategoria(Long id) {
        var categoria = buscarCategoria(id);
        categoriaRepository.delete(categoria);

    }

    //MÉTODO AUXILIAR
    public Categoria buscarCategoria(Long id){
        var categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi encontrada categoria com ID: " + id));

        return categoria;
    }
}
