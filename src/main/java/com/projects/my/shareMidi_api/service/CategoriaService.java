package com.projects.my.shareMidi_api.service;

import com.projects.my.shareMidi_api.dto.AtualizarCategoriaDto;
import com.projects.my.shareMidi_api.dto.CriarCategoriaDto;
import com.projects.my.shareMidi_api.dto.DetalharCategoriaDto;
import com.projects.my.shareMidi_api.exception.CategoriaNaoEncontradaException;
import com.projects.my.shareMidi_api.exception.CategoriaNaoPermiteExclusaoException;
import com.projects.my.shareMidi_api.model.Categoria;
import com.projects.my.shareMidi_api.repository.CategoriaRepository;
import com.projects.my.shareMidi_api.validation.CategoriaValidation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final List<CategoriaValidation> validation;

    public CategoriaService(CategoriaRepository categoriaRepository, List<CategoriaValidation> validation) {
        this.categoriaRepository = categoriaRepository;
        this.validation = validation;
    }

    public Page<DetalharCategoriaDto> exibirCategorias(Pageable pageable) {
        return categoriaRepository.findAll(pageable).map(DetalharCategoriaDto::new);

    }

    public Categoria exibirUnicaCategoria(Long id) {
        return buscarCategoria(id);

    }

    public Categoria criarCategoria(CriarCategoriaDto dto) {
        validation.forEach(v -> v.validar(dto));
        Categoria categoria = new Categoria(dto);
        return categoriaRepository.save(categoria);

    }

    public Categoria atualizarCategoria(Long id, AtualizarCategoriaDto dto) {
        validation.forEach(v -> v.validar(dto));
        var categoria = buscarCategoria(id);
        categoria.atualizar(dto);
        return categoriaRepository.save(categoria);

    }

    public void deletarCategoria(Long id) {
        if (id.equals(1L)) {
            throw new CategoriaNaoPermiteExclusaoException("Não é permitido excluir a categoria LIVRE");
        } else {
            var categoria = buscarCategoria(id);
            categoriaRepository.delete(categoria);

        }

    }

    //MÉTODO AUXILIAR
    public Categoria buscarCategoria(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException("Não foi encontrada categoria com ID: " + id));

    }
}
