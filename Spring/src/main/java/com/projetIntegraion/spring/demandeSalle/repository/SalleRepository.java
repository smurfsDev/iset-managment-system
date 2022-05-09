package com.projetIntegraion.spring.demandeSalle.repository;

import com.projetIntegraion.spring.demandeSalle.entity.Salle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface SalleRepository extends JpaRepository<Salle, Long>{

}
