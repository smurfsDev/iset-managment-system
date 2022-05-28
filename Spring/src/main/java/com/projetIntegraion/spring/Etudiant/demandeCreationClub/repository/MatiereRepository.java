package com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Matiere;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    Page<Matiere> findByClasseId(Long id,Pageable pageable);
    Boolean existsByNom(String nom);
    Page<Matiere> findByEnseignantId(Long id,Pageable pageable);

    
}
