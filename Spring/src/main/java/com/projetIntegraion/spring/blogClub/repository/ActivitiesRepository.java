package com.projetIntegraion.spring.blogClub.repository;

import com.projetIntegraion.spring.blogClub.entity.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivitiesRepository extends JpaRepository<Activity, Long> {
    
}
