package com.projetIntegraion.spring.demandeMateriel.repository;


import java.util.List;

import com.projetIntegraion.spring.demandeMateriel.entity.Materiel;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories

public interface MaterielRepository extends JpaRepository<Materiel, Long> {
    List<Materiel> findByCategorieId(Long id);

}
