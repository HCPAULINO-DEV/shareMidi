package com.projects.my.shareMidi_api.controller;

import com.projects.my.shareMidi_api.dto.AtualizarCategoriaDto;
import com.projects.my.shareMidi_api.dto.CriarCategoriaDto;
import com.projects.my.shareMidi_api.dto.DetalharCategoriaDto;
import com.projects.my.shareMidi_api.dto.DetalharVideoDto;
import com.projects.my.shareMidi_api.model.Categoria;
import com.projects.my.shareMidi_api.service.CategoriaService;
import com.projects.my.shareMidi_api.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final VideoService videoService;

    public CategoriaController(CategoriaService categoriaService, VideoService videoService) {
        this.categoriaService = categoriaService;
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<Page<DetalharCategoriaDto>> exibirCategorias(@PageableDefault(sort = "id", size = 10) Pageable pageable){
        var categorias = categoriaService.exibirCategorias(pageable);

        return ResponseEntity.ok(categorias);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharCategoriaDto> exibirUnicaCategoria(@PathVariable Long id){
        var categoria = categoriaService.exibirUnicaCategoria(id);

        return ResponseEntity.ok(new DetalharCategoriaDto(categoria));

    }

    @GetMapping("/{id}/videos")
    public ResponseEntity<Page<DetalharVideoDto>> exibirVideosPorCategoria(@PathVariable Categoria categoria, @PageableDefault(sort = "id", size = 10) Pageable pageable){
        var videos = videoService.exibirVideoPorCategoria(categoria, pageable);

        return ResponseEntity.ok(videos);

    }

    @Transactional
    @PostMapping
    public ResponseEntity<DetalharCategoriaDto> criarCategoria(@RequestBody @Valid CriarCategoriaDto dto, UriComponentsBuilder uriComponentsBuilder){
        var categoria = categoriaService.criarCategoria(dto);
        var uri = uriComponentsBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalharCategoriaDto(categoria));

    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<DetalharCategoriaDto> atualizarCategoria(@PathVariable Long id, @RequestBody @Valid AtualizarCategoriaDto dto){
        var categoria = categoriaService.atualizarCategoria(id, dto);

        return ResponseEntity.ok(new DetalharCategoriaDto(categoria));

    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id){
        categoriaService.deletarCategoria(id);

        return ResponseEntity.noContent().build();

    }
}
