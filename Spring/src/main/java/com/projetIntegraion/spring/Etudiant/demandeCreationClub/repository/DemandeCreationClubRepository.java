package com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository;

import java.util.Optional;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.DemandeCreationClub;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

@EnableJpaRepositories
public interface DemandeCreationClubRepository extends JpaRepository<DemandeCreationClub, Long> {
    // Page<DemandeCreationClub> findByresponsableClubId(Long id,Pageable pageable);
    Page<DemandeCreationClub> findByNomClubContains(String nomClub, Pageable pageable);

    @Query(" select c from DemandeCreationClub c where c.nomClub = ?1")
    Optional<DemandeCreationClub> findClubrWithClubName(String name);

    Optional<DemandeCreationClub> findByNomClub(String name);

    Page<DemandeCreationClub> findByResponsableClub(User responsableClub, Pageable pageable);

    @Query("select case when count(c)> 0 then true else false end from DemandeCreationClub c where lower(c.nomClub) like lower(:nomClub)")
    boolean existsByname(@Param("nomClub") String nomClub);
}
