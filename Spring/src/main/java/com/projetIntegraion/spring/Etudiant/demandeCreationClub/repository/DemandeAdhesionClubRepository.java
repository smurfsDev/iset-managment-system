package com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.DemandeAdhesionClub;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
public interface DemandeAdhesionClubRepository extends JpaRepository<DemandeAdhesionClub, Long>{
    DemandeAdhesionClub findByClubIdAndEtudiantId(Long id, Long id2);
}
