package com.projects.my.shareMidi_api.model;

import com.projects.my.shareMidi_api.dto.AtualizarVideoDto;
import com.projects.my.shareMidi_api.dto.CriarVideoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "video")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String url;

    public Video(CriarVideoDto dtoCriar){
        this.titulo = dtoCriar.titulo();
        this.descricao = dtoCriar.descricao();
        this.url = dtoCriar.url();

    }

    public void atualizar(AtualizarVideoDto dto){
        if (dto.titulo() != null){
            this.titulo = dto.titulo();
        }
        if (dto.descricao() != null){
            this.descricao = dto.descricao();
        }
        if (dto.url() != null){
            this.url = dto.url();
        }
    }

}
