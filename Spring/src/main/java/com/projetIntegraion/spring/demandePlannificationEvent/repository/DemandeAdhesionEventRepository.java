package com.projetIntegraion.spring.demandePlannificationEvent.repository;

import com.projetIntegraion.spring.demandePlannificationEvent.entity.DemandeAdhesionEvent;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface DemandeAdhesionEventRepository extends JpaRepository<DemandeAdhesionEvent, Long>  {
    DemandeAdhesionEvent findByClubIdAndEtudiantId(Long id, int id2);
    DemandeAdhesionEvent findByEventIdAndEtudiantId(Long id, int id2);
    
    Page<DemandeAdhesionEvent> findByEtudiantId(int id, Pageable pageable);
    Page<DemandeAdhesionEvent> findByClubId(Long id, Pageable pageable);

    @Query("select e from DemandeAdhesionEvent e where e.status = 0 and e.club = ?1")
	Page<DemandeAdhesionEvent> findNonApprouvedDemandesParClub(Long id,Pageable pageable); 
}
