package com.projects.my.shareMidi_api.validation;

import com.projects.my.shareMidi_api.dto.AtualizarCategoriaDto;
import com.projects.my.shareMidi_api.dto.AtualizarVideoDto;
import com.projects.my.shareMidi_api.dto.CriarCategoriaDto;
import com.projects.my.shareMidi_api.dto.CriarVideoDto;
import com.projects.my.shareMidi_api.exception.CategoriaJaExistenteException;
import com.projects.my.shareMidi_api.exception.VideoJaExistenteException;
import com.projects.my.shareMidi_api.repository.VideoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TituloVideoUnicoValidationTest {

    @InjectMocks
    private TituloVideoUnicoValidation tituloVideoUnicoValidation;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private CriarVideoDto dto;

    @Test
    public void deveRetornarUmaExcecaoVideoJaExistenteNaCriacao(){
        CriarVideoDto criarDto = new CriarVideoDto("Título", "Descrição", "url", null);
        BDDMockito.given(videoRepository.existsByTitulo(criarDto.titulo())).willReturn(true);

        VideoJaExistenteException exception = Assertions.assertThrows(VideoJaExistenteException.class, () -> tituloVideoUnicoValidation.validar(criarDto));
        Assertions.assertEquals("Vídeo já existente", exception.getMessage());

    }

    @Test
    public void deveRetornarUmaExcecaoCategoriaJaExistenteNaAtualizacao(){
        AtualizarVideoDto atualizarDtp = new AtualizarVideoDto("Título", "Descrição", "url", null);
        BDDMockito.given(videoRepository.existsByTitulo(atualizarDtp.titulo())).willReturn(true);

        VideoJaExistenteException exception = Assertions.assertThrows(VideoJaExistenteException.class, () -> tituloVideoUnicoValidation.validar(atualizarDtp));
        Assertions.assertEquals("Vídeo já existente", exception.getMessage());

    }

    @Test
    public void naoDeveLancarExcecaoSeCategoriaNaoExistir(){
        BDDMockito.given(videoRepository.existsByTitulo(dto.titulo())).willReturn(false);

        Assertions.assertDoesNotThrow(() -> tituloVideoUnicoValidation.validar(dto));

    }

}