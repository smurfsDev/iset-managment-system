package com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Classe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
public interface ClasseRepository extends JpaRepository<Classe, Long>{
    Page<Classe> findByDepartementId(Long id,Pageable pageable);
    Boolean existsByNom(String nom);
    Boolean existsByAbreviation(String abreviation);
}
