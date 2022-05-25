package com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.DemandeCreationClub;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
public interface DemandeCreationClubRepository extends JpaRepository<DemandeCreationClub, Long>{
    // Page<DemandeCreationClub> findByresponsableClubId(Long id,Pageable pageable);
    Page<DemandeCreationClub> findByNomClubContains(String nomClub,Pageable pageable);
    Page<DemandeCreationClub> findByResponsableClub(User responsableClub,Pageable pageable);
}
