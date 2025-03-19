package com.projects.my.shareMidi_api.validation;

import com.projects.my.shareMidi_api.dto.AtualizarCategoriaDto;
import com.projects.my.shareMidi_api.dto.CriarCategoriaDto;
import com.projects.my.shareMidi_api.exception.CategoriaJaExistenteException;
import com.projects.my.shareMidi_api.repository.CategoriaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TituloCategoriaUnicoValidationTest {

    @InjectMocks
    private TituloCategoriaUnicoValidation tituloCategoriaUnicoValidatio;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CriarCategoriaDto dto;

    @Test
    public void deveRetornarUmaExcecaoCategoriaJaExistenteNaCriacao(){
        CriarCategoriaDto criarDto = new CriarCategoriaDto("Título", "Cor");
        BDDMockito.given(categoriaRepository.existsByTitulo(criarDto.titulo())).willReturn(true);

        CategoriaJaExistenteException exception = Assertions.assertThrows(CategoriaJaExistenteException.class, () -> tituloCategoriaUnicoValidatio.validar(criarDto));
        Assertions.assertEquals("Categoria já existente", exception.getMessage());

    }

    @Test
    public void deveRetornarUmaExcecaoCategoriaJaExistenteNaAtualizacao(){
        AtualizarCategoriaDto criarDto = new AtualizarCategoriaDto("Título", "Cor");
        BDDMockito.given(categoriaRepository.existsByTitulo(criarDto.titulo())).willReturn(true);

        CategoriaJaExistenteException exception = Assertions.assertThrows(CategoriaJaExistenteException.class, () -> tituloCategoriaUnicoValidatio.validar(criarDto));
        Assertions.assertEquals("Categoria já existente", exception.getMessage());

    }

    @Test
    public void naoDeveLancarExcecaoSeCategoriaNaoExistir(){
        BDDMockito.given(categoriaRepository.existsByTitulo(dto.titulo())).willReturn(false);

        Assertions.assertDoesNotThrow(() -> tituloCategoriaUnicoValidatio.validar(dto));

    }

}