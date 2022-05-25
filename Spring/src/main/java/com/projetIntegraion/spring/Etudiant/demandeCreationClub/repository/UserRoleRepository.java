package com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository;

import java.util.Optional;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    Optional<UserRole> findByRoleIdAndUserId(Long id, int userId);
        
}
