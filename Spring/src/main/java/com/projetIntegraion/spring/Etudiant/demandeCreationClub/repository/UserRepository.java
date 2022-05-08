package com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories

public interface UserRepository extends JpaRepository<User, Long> {

}
