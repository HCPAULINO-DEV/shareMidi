package com.projects.my.shareMidi_api.service;

import com.projects.my.shareMidi_api.model.Categoria;
import com.projects.my.shareMidi_api.repository.CategoriaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Test
    public void DeveRetornarUmaExcecaoIdNaoEncontrado(){
        Long id = 1L;

        BDDMockito.when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> categoriaService.buscarCategoria(id));
        Assertions.assertEquals("Não foi encontrada categoria com ID: " + id, exception.getMessage());

    }

    @Test
    public void DeveRetornarUmaCategoria(){
        Long id = 1L;
        Categoria categoria = new Categoria(id, "Titulo", "Verde", null);
        BDDMockito.when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoria));

        Assertions.assertDoesNotThrow(() -> categoriaService.buscarCategoria(id));

    }



}