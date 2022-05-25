package com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.DemandeAdhesionClub;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
public interface DemandeAdhesionClubRepository extends JpaRepository<DemandeAdhesionClub, Long>{
    DemandeAdhesionClub findByClubIdAndEtudiantId(Long id, int id2);
    Page<DemandeAdhesionClub> findByEtudiantId(int id, Pageable pageable);
    Page<DemandeAdhesionClub> findByClubId(Long id, Pageable pageable);
}
