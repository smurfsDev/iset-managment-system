package com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Member;
import com.projetIntegraion.spring.blogClub.entity.Club;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
public interface MemberRepository extends JpaRepository<Member, Long>{

    Page<Member> findByClub(Club club, Pageable pageable);
    
}
