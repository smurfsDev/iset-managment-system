package com.projetIntegraion.spring.blogClub.repository;

import com.projetIntegraion.spring.blogClub.entity.Project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
}
