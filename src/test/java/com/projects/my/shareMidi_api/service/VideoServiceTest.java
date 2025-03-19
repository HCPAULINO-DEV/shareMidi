package com.projects.my.shareMidi_api.service;

import com.projects.my.shareMidi_api.model.Video;
import com.projects.my.shareMidi_api.repository.VideoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VideoServiceTest {

    @InjectMocks
    private VideoService videoService;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private Video video;

    @Mock
    private CategoriaService categoriaService;

    @Test
    public void DeveRetornarUmaExcecaooIdNaoEncontrado(){
        Long id = 1L;
        BDDMockito.when(videoRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> videoService.buscarVideo(id));
        Assertions.assertEquals("Vídeo não encontrado com ID: " + id, exception.getMessage());

    }

    @Test
    public void DeveRetornarUmIdValido(){
        Long id = 1L;
        BDDMockito.when(videoRepository.findById(id)).thenReturn(Optional.of(video));

        Assertions.assertDoesNotThrow(() -> videoService.buscarVideo(id));

    }

    @Test
    public void deveRetornarUmaExcecaoIdNaoEncontradoAoBuscarVideo() {
        Long id = 1L;

        BDDMockito.when(videoRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> videoService.buscarVideo(id));

        Assertions.assertEquals("Vídeo não encontrado com ID: " + id, exception.getMessage());

    }


}