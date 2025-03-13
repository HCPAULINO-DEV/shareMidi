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

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Video(CriarVideoDto dtoCriar, Categoria categoria){
        this.titulo = dtoCriar.titulo();
        this.descricao = dtoCriar.descricao();
        this.url = dtoCriar.url();
        this.categoria = categoria;

    }

    public void atualizar(AtualizarVideoDto dto, Categoria categoria){
        if (dto.titulo() != null){
            this.titulo = dto.titulo();
        }
        if (dto.descricao() != null){
            this.descricao = dto.descricao();
        }
        if (dto.url() != null){
            this.url = dto.url();
        }
        if (dto.categoria() != null){
            this.categoria = categoria;
        }
    }

}
