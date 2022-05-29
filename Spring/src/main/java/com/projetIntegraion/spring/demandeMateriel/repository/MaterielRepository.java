package com.projetIntegraion.spring.demandeMateriel.repository;

import java.util.List;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.demandeMateriel.entity.Materiel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories

public interface MaterielRepository extends JpaRepository<Materiel, Long> {
    List<Materiel> findByCategorieId(Long id);

    Boolean existsByTitre(String titre);

    @Query("select u from User u join Materiel m on m.responsable=u where m.responsable=u group by u")
    List<User> findByResponsable();

}
