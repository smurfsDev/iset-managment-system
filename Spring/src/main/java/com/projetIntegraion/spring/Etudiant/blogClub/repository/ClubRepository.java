package com.projetIntegraion.spring.Etudiant.blogClub.repository;

import com.projetIntegraion.spring.Etudiant.blogClub.entity.Club;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long>{
    
}
