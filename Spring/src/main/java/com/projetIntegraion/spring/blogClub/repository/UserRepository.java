package com.projetIntegraion.spring.blogClub.repository;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
