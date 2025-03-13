package com.projects.my.shareMidi_api.controller;

import com.projects.my.shareMidi_api.dto.DetalharCategoriaDto;
import com.projects.my.shareMidi_api.service.CategoriaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<Page<DetalharCategoriaDto>> exibirCategorias(Pageable pageable){
        var categorias = categoriaService.exibirCategorias(pageable);

        return ResponseEntity.ok(categorias);

    }
}
