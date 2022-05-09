package com.projetIntegraion.spring.demandeSalle.repository;

import com.projetIntegraion.spring.demandeSalle.entity.DemandeSalle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories  

public interface DemandeSalleRepository extends JpaRepository<DemandeSalle, Long>{

}
