package com.projects.my.shareMidi_api.service;

import com.projects.my.shareMidi_api.dto.AtualizarCategoriaDto;
import com.projects.my.shareMidi_api.dto.CriarCategoriaDto;
import com.projects.my.shareMidi_api.dto.DetalharCategoriaDto;
import com.projects.my.shareMidi_api.model.Categoria;
import com.projects.my.shareMidi_api.repository.CategoriaRepository;
import com.projects.my.shareMidi_api.validation.CategoriaValidation;
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
        return categoriaRepository.findAll(pageable)
                .map(DetalharCategoriaDto::new);

    }

    public Categoria exibirUnicaCategoria(Long id) {
        var categoria = buscarCategoria(id);

        return categoria;

    }

    public Categoria criarCategoria(CriarCategoriaDto dto) {
        validation.forEach(v -> v.validar(dto));
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
