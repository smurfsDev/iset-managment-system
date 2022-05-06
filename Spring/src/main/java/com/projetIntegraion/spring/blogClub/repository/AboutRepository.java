package com.projetIntegraion.spring.blogClub.repository;

import com.projetIntegraion.spring.blogClub.entity.About;


import org.springframework.data.jpa.repository.JpaRepository;


public interface AboutRepository extends JpaRepository<About, Long> {
    // @Query("select a from About a where a.club = ?1")
    // public About findByClubId(Long id);
}
