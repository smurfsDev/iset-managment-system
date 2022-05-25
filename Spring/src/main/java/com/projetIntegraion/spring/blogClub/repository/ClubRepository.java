package com.projetIntegraion.spring.blogClub.repository;

import java.util.Optional;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.blogClub.entity.Club;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long>{
    // Page<Club> findByresponsableClubId(Long id,Pageable pageable);
    // Page<Club> findByNomClubContains(String nomClub,Pageable pageable);
    Optional<Club> findByResponsableClubId(int id);
    Optional<Club> findByResponsableClub(User id);

}
