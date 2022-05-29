package com.projetIntegraion.spring.demandeMateriel.repository;

import com.projetIntegraion.spring.demandeMateriel.entity.CategorieMateriel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories

public interface CategorieMaterielRepository extends JpaRepository<CategorieMateriel, Long> {
    Boolean existsByTitre(String titre);
}
