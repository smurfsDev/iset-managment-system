package com.projetIntegraion.spring.demandeSalle.repository;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.demandeSalle.entity.DemandeSalle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories

public interface DemandeSalleRepository extends JpaRepository<DemandeSalle, Long> {
    Page<DemandeSalle> findByResponsable(User user, Pageable pageable);

    @Query(value = "select * from demande_salle D join demande_salle_salle DS on D.id = DS.id_demmande join salle S on S.id = DS.id_salle join departement Dp on Dp.id = S.departement_id where Dp.chef_department_id =?1", nativeQuery = true)
    Page<DemandeSalle> findByDestinataire(Integer id, Pageable pageable);

}
