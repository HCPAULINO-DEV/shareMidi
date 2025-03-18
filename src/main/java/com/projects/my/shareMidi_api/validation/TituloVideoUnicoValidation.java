package com.projects.my.shareMidi_api.validation;

import com.projects.my.shareMidi_api.dto.AtualizarVideoDto;
import com.projects.my.shareMidi_api.dto.CriarVideoDto;
import com.projects.my.shareMidi_api.exception.VideoJaExistenteException;
import com.projects.my.shareMidi_api.repository.VideoRepository;
import org.springframework.stereotype.Component;

@Component
public class TituloVideoUnicoValidation implements VideoValidation {

    private final VideoRepository videoRepository;

    public TituloVideoUnicoValidation(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public void validar(Object object) {
        if (object instanceof CriarVideoDto criarDto) {
            if (videoRepository.existsByTitulo(criarDto.titulo())) {
                throw new VideoJaExistenteException("Vídeo já existente");
            }
        } else if (object instanceof AtualizarVideoDto atualizarDto) {
            if (videoRepository.existsByTitulo(atualizarDto.titulo())) {
                throw new VideoJaExistenteException("Vídeo já existente");
            }
        } else {
            throw new IllegalArgumentException("Tipo de DTO não suportado.");
        }
    }
}
