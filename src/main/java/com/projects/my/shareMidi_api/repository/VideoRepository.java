package com.projects.my.shareMidi_api.repository;

import com.projects.my.shareMidi_api.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
