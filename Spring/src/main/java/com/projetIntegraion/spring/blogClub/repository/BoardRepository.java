package com.projetIntegraion.spring.blogClub.repository;

import java.util.List;

import com.projetIntegraion.spring.blogClub.entity.Board;
import com.projetIntegraion.spring.blogClub.entity.Club;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByClub(Club club);
}
