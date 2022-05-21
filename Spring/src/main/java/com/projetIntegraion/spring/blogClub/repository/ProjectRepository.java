package com.projetIntegraion.spring.blogClub.repository;

import java.util.List;

import com.projetIntegraion.spring.blogClub.entity.Club;
import com.projetIntegraion.spring.blogClub.entity.Project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByClub(Club club);
}
