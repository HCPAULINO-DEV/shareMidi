package com.projects.my.shareMidi_api.repository;

import com.projects.my.shareMidi_api.model.Categoria;
import com.projects.my.shareMidi_api.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Page<Video> findByCategoria(Categoria categoria, Pageable pageable);

    @Query("SELECT v FROM Video v WHERE LOWER(v.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    Page<Video> findByTitulo(@Param("titulo") String titulo, Pageable pageable);

    Boolean existsByTitulo(String titulo);

}
