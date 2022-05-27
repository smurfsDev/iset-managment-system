package com.projetIntegraion.spring.blogClub.repository;

import java.util.List;

import com.projetIntegraion.spring.blogClub.entity.Activity;
import com.projetIntegraion.spring.blogClub.entity.Club;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
public interface ActivitiesRepository extends JpaRepository<Activity, Long> {
   List<Activity> findByClub(Club club);
}
