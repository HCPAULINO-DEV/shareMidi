package com.projects.my.shareMidi_api.service;

import com.projects.my.shareMidi_api.dto.AtualizarVideoDto;
import com.projects.my.shareMidi_api.dto.CriarVideoDto;
import com.projects.my.shareMidi_api.dto.DetalharVideoDto;
import com.projects.my.shareMidi_api.exception.VideoNaoEncontradoException;
import com.projects.my.shareMidi_api.model.Categoria;
import com.projects.my.shareMidi_api.model.Video;
import com.projects.my.shareMidi_api.repository.CategoriaRepository;
import com.projects.my.shareMidi_api.repository.VideoRepository;
import com.projects.my.shareMidi_api.validation.CategoriaValidation;
import com.projects.my.shareMidi_api.validation.VideoValidation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final CategoriaService categoriaService;
    private final List<VideoValidation> validation;

    public VideoService(VideoRepository videoRepository, CategoriaService categoriaService, List<VideoValidation> validation) {
        this.videoRepository = videoRepository;
        this.categoriaService = categoriaService;
        this.validation = validation;
    }

    public Page<DetalharVideoDto> exibirVideos(Pageable pageable, String titulo){
        if (titulo != null) {
            return videoRepository.findByTitulo(titulo, pageable).map(DetalharVideoDto::new);
        }

        return videoRepository.findAll(pageable).map(DetalharVideoDto::new);

    }

    public Video exibirUnicoVideo(Long id){
        return buscarVideo(id);

    }

    public Page<DetalharVideoDto> exibirVideoPorCategoria(Categoria categoria, Pageable pageable){
        return videoRepository.findByCategoria(categoria, pageable).map(DetalharVideoDto::new);

    }

    public Video criarVideo(CriarVideoDto dto) {
        validation.forEach(v -> v.validar(dto));
        Categoria categoria;


        //VALIDAÇÃO - Se categoria for nula, setar categoria padrão ID: 1
        if (dto.categoria() == null || dto.categoria().equals("")){
            categoria = categoriaService.buscarCategoria(1L);
        } else{
            categoria = categoriaService.buscarCategoria(dto.categoria());
        }

        Video video = new Video(dto, categoria);
        return videoRepository.save(video);

    }

    public Video atualizarVideo(Long id, AtualizarVideoDto dto) {
        validation.forEach(v -> v.validar(dto));
        var video = buscarVideo(id);
        Categoria categoria;

        //VALIDAÇÃO - Se categoria for nula, setar categoria padrão ID: 1
        if (dto.categoria() == null || dto.categoria().equals("")){
            categoria = categoriaService.buscarCategoria(1L);
        } else{
            categoria = categoriaService.buscarCategoria(dto.categoria());
        }

        video.atualizar(dto, categoria);
        return videoRepository.save(video);

    }

    public void deletarVideo(Long id){
        var video = buscarVideo(id);
        videoRepository.delete(video);

    }

    //MÉTODO AUXILIAR
    public Video buscarVideo(Long id){
        return videoRepository.findById(id)
                .orElseThrow(() -> new VideoNaoEncontradoException("Vídeo não encontrado com ID: " + id));

    }

}
