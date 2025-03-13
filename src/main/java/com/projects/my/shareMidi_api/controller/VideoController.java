package com.projects.my.shareMidi_api.controller;

import com.projects.my.shareMidi_api.dto.AtualizarVideoDto;
import com.projects.my.shareMidi_api.dto.CriarVideoDto;
import com.projects.my.shareMidi_api.dto.DetalharVideoDto;
import com.projects.my.shareMidi_api.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<Page<DetalharVideoDto>> exibirVideos(@PageableDefault(sort = "id", size = 10) Pageable pageable) {
        var videos = videoService.exibirVideos(pageable);

        return ResponseEntity.ok(videos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharVideoDto> exibirUnicoVideo(@PathVariable Long id) {
        var video = videoService.exibirUnicoVideo(id);

        return ResponseEntity.ok(new DetalharVideoDto(video));

    }

    @PostMapping("/cadastrar")
    public ResponseEntity<DetalharVideoDto> criarVideo(@RequestBody @Valid CriarVideoDto dto, UriComponentsBuilder uriComponentsBuilder) {
        var video = videoService.criarVideo(dto);
        var uri = uriComponentsBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalharVideoDto(video));

    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalharVideoDto> atualizarVideo(@PathVariable Long id, @RequestBody @Valid AtualizarVideoDto dto) {
        var video = videoService.atualizarVideo(id, dto);

        return ResponseEntity.ok(new DetalharVideoDto(video));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVideo(@PathVariable Long id) {
        videoService.deletarVideo(id);

        return ResponseEntity.noContent().build();

    }

}
