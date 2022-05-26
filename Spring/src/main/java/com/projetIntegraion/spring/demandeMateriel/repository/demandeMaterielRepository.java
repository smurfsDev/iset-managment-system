package com.projetIntegraion.spring.demandeMateriel.repository;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.demandeMateriel.entity.DemandeMateriel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface demandeMaterielRepository extends JpaRepository<DemandeMateriel, Long> {
    Page<DemandeMateriel> findByResponsableClub(User id, Pageable pageable);
}
