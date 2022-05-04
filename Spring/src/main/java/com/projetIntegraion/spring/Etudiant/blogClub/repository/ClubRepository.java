package com.projetIntegraion.spring.Etudiant.blogClub.repository;

import com.projetIntegraion.spring.Etudiant.blogClub.entity.Club;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long>{
    // Page<Club> findByresponsableClubId(Long id,Pageable pageable);
    // Page<Club> findByNomClubContains(String nomClub,Pageable pageable);

}
