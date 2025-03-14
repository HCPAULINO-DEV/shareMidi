package com.projects.my.shareMidi_api.service;

import com.projects.my.shareMidi_api.dto.AtualizarVideoDto;
import com.projects.my.shareMidi_api.dto.CriarVideoDto;
import com.projects.my.shareMidi_api.dto.DetalharVideoDto;
import com.projects.my.shareMidi_api.model.Categoria;
import com.projects.my.shareMidi_api.model.Video;
import com.projects.my.shareMidi_api.repository.CategoriaRepository;
import com.projects.my.shareMidi_api.repository.VideoRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final CategoriaService categoriaService;

    public VideoService(VideoRepository videoRepository, CategoriaService categoriaService) {
        this.videoRepository = videoRepository;
        this.categoriaService = categoriaService;
    }

    public Page<DetalharVideoDto> exibirVideos(Pageable pageable, String titulo){
        if (titulo != null) {
            return videoRepository.findByTitulo(titulo, pageable).map(DetalharVideoDto::new);
        }

        return videoRepository.findAll(pageable).map(DetalharVideoDto::new);

    }

    public Video exibirUnicoVideo(Long id){
        var video = buscarVideo(id);

        return video;

    }

    public Page<DetalharVideoDto> exibirVideoPorCategoria(Categoria categoria, Pageable pageable){
        return videoRepository.findByCategoria(categoria, pageable).map(DetalharVideoDto::new);

    }

    public Video criarVideo(CriarVideoDto dto) {
        Categoria categoria;

        if (dto.categoria() == null || dto.categoria().equals("")){
            categoria = categoriaService.buscarCategoria(1L);
        } else{
            categoria = categoriaService.buscarCategoria(dto.categoria());
        }

        Video video = new Video(dto, categoria);
        videoRepository.save(video);

        return video;

    }

    public Video atualizarVideo(Long id, AtualizarVideoDto dto) {
        var video = buscarVideo(id);
        Categoria categoria = categoriaService.buscarCategoria(dto.categoria());
        video.atualizar(dto, categoria);
        videoRepository.save(video);

        return video;

    }

    public void deletarVideo(Long id){
        var video = buscarVideo(id);
        videoRepository.delete(video);

    }

    //MÉTODO AUXILIAR
    public Video buscarVideo(Long id){
        var usuario = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vídeo não encontrado pelo ID: " + id));

        return usuario;

    }
}
