package com.projetIntegraion.spring.blogClub.repository;

import com.projetIntegraion.spring.blogClub.entity.Activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
public interface ActivitiesRepository extends JpaRepository<Activity, Long> {
   
}
