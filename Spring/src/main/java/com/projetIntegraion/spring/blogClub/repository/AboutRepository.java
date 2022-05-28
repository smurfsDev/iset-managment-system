package com.projetIntegraion.spring.blogClub.repository;

import java.util.List;

import com.projetIntegraion.spring.blogClub.entity.About;
import com.projetIntegraion.spring.blogClub.entity.Club;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AboutRepository extends JpaRepository<About, Long> {
    // @Query("select a from About a where a.club = ?1")
    // public About findByClubId(Long id);
    List<About> findByClub(Club club);
    Page<About> findByClubId(Long id, Pageable pageable);

}
