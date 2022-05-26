package com.projetIntegraion.spring.demandePlannificationEvent.repository;

import java.util.Optional;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.demandePlannificationEvent.entity.DemandeEvent;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface DemandeEventRepository extends JpaRepository<DemandeEvent, Long> {
    Page<DemandeEvent> findByresponsableClubId(Long id,Pageable pageable);
    // Page<DemandeEvent> findByNomClubContains(String nomClub, Pageable pageable);

    // @Query(" select c from DemandeEvent c where c.nomClub = ?1")
    // Optional<DemandeEvent> findClubrWithClubName(String name);

    // Optional<DemandeEvent> findByNomClub(String name);

    Page<DemandeEvent> findByResponsableClub(User responsableClub, Pageable pageable);

    Page<DemandeEvent> findByClubId(Long id, Pageable pageable);


}
