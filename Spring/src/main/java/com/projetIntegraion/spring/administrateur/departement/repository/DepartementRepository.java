package com.projetIntegraion.spring.administrateur.departement.repository;

import com.projetIntegraion.spring.administrateur.departement.entity.Departement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories

public interface DepartementRepository extends JpaRepository<Departement, Long> {

}
