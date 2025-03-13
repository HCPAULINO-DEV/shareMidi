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
        Page<Video> page = videoRepository.findAll(pageable);

        if (page.isEmpty()){
            throw new RuntimeException("Nenhum vídeo foi encontrado");
        }

        Page<DetalharVideoDto> dto = page.map(DetalharVideoDto::new);

        return dto;

    }

    public Video exibirUnicoVideo(Long id){
        var video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vídeo não encontrado pelo ID: " + id));

        return video;

    }

    public Video criarVideo(@Valid CriarVideoDto dto) {
        Video video = new Video(dto);
        videoRepository.save(video);

        return video;

    }

    public Video atualizarVideo(Long id, AtualizarVideoDto dto) {
        var video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vídeo não encontrado pelo ID: " + id));

        video.atualizar(dto);
        videoRepository.save(video);

        return video;

    }

    public void deletarVideo(Long id){
        var video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vídeo não encontrado pelo ID: " + id));

        videoRepository.delete(video);

    }
}
