package com.projetIntegraion.spring.demandePlannificationEvent.repository;

import com.projetIntegraion.spring.demandePlannificationEvent.entity.DemandeAdhesionEvent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface DemandeAdhesionEventRepository extends JpaRepository<DemandeAdhesionEvent, Long>  {
    
}
