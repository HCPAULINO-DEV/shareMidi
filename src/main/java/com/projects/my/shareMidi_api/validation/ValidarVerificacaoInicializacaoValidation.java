package com.projects.my.shareMidi_api.validation;

import com.projects.my.shareMidi_api.service.CategoriaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ValidarVerificacaoInicializacaoValidation implements CommandLineRunner {

    private final CategoriaService categoriaService;

    public ValidarVerificacaoInicializacaoValidation(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verifica se a categoria LIVRE com ID = 1 existe
        if (categoriaService.buscarCategoria(1L) == null || !categoriaService.buscarCategoria(1L).getTitulo().equals("LIVRE")) {
            // Se não existir, lança uma exceção para impedir o sistema de iniciar
            throw new RuntimeException("PARA INICIAR O SISTEMA A CATEGORIA LIVRE COM ID 1 DEVE SER INSERIDA NO BANCO DE DADOS");
        }
    }

}
