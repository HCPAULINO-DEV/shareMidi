package com.projects.my.shareMidi_api.repository;

import com.projects.my.shareMidi_api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
