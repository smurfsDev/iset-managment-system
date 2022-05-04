package com.projetIntegraion.spring.Etudiant.blogClub.repository;

import com.projetIntegraion.spring.Etudiant.blogClub.entity.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivitiesRepository extends JpaRepository<Activity, Long> {
    
}
