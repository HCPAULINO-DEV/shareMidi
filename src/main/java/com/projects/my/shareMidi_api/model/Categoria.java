package com.projects.my.shareMidi_api.model;

import com.projects.my.shareMidi_api.dto.AtualizarCategoriaDto;
import com.projects.my.shareMidi_api.dto.CriarCategoriaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false)
    private String cor;

    public Categoria(CriarCategoriaDto dto){
        this.titulo = dto.titulo();
        this.cor = dto.cor();
    }

    public void atualizar(AtualizarCategoriaDto dto){
        if (dto.titulo() != null){
            this.titulo = dto.titulo();
        }
        if (dto.cor() != null){
            this.cor = dto.cor();
        }
    }
}
