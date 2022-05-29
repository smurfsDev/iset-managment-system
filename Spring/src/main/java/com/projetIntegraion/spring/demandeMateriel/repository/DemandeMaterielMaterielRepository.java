package com.projetIntegraion.spring.demandeMateriel.repository;

import java.util.List;

import com.projetIntegraion.spring.demandeMateriel.entity.DemandeMaterielMateriel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface DemandeMaterielMaterielRepository extends JpaRepository<DemandeMaterielMateriel, Long> {
    DemandeMaterielMateriel findByMaterielId(Long id);

    DemandeMaterielMateriel findByDemandeMaterielIdAndMaterielId(Long id, Long idm);

    List<DemandeMaterielMateriel> findByDemandeMaterielId(Long id);

}
