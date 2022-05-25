package com.projetIntegraion.spring.demandeSalle.repository;

import com.projetIntegraion.spring.demandeSalle.entity.DemandeSalle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories

public interface DemandeSalleRepository extends JpaRepository<DemandeSalle, Long> {
    Page<DemandeSalle> findByIdResponsable(Integer id, Pageable pageable);

}
