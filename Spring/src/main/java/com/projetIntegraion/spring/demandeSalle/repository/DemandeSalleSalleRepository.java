package com.projetIntegraion.spring.demandeSalle.repository;

import com.projetIntegraion.spring.demandeSalle.entity.DemandeSalleSalle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories

public interface DemandeSalleSalleRepository extends JpaRepository<DemandeSalleSalle, Long>{
    DemandeSalleSalle  findByDemandeSalleId(Long id);

}
