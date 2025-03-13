package com.projects.my.shareMidi_api.service;

import com.projects.my.shareMidi_api.dto.AtualizarVideoDto;
import com.projects.my.shareMidi_api.dto.CriarVideoDto;
import com.projects.my.shareMidi_api.dto.DetalharVideoDto;
import com.projects.my.shareMidi_api.model.Video;
import com.projects.my.shareMidi_api.repository.VideoRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Page<DetalharVideoDto> exibirVideos(Pageable pageable){
        return videoRepository.findAll(pageable).map(DetalharVideoDto::new);

    }

    public Video exibirUnicoVideo(Long id){
        var video = buscarVideo(id);

        return video;

    }

    public Video criarVideo(@Valid CriarVideoDto dto) {
        Video video = new Video(dto);
        videoRepository.save(video);

        return video;

    }

    public Video atualizarVideo(Long id, AtualizarVideoDto dto) {
        var video = buscarVideo(id);
        video.atualizar(dto);
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
