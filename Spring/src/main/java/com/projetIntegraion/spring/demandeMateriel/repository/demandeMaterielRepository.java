package com.projetIntegraion.spring.demandeMateriel.repository;

import java.util.List;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.demandeMateriel.entity.DemandeMateriel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface demandeMaterielRepository extends JpaRepository<DemandeMateriel, Long> {
    Page<DemandeMateriel> findByResponsableClub(User id, Pageable pageable);

    Page<DemandeMateriel> findByDestinataire(User id, Pageable pageable);

    @Query(value = "select materiel.titre ,'De quantite :' ,demande_materiel_materiel.quantite from demande_materiel join demande_materiel_materiel on demande_materiel.id = demande_materiel_materiel.id_demmande join materiel on demande_materiel_materiel.id_materiel = materiel.id where demande_materiel.destinataire_id = ?1", nativeQuery = true)
    List<String> findMaterielByDestinataireId(Integer id);

    @Query(value = "select demande_materiel_materiel.quantite from demande_materiel join demande_materiel_materiel on demande_materiel.id = demande_materiel_materiel.id_demmande join materiel on demande_materiel_materiel.id_materiel = materiel.id where demande_materiel.destinataire_id = ?1", nativeQuery = true)
    List<Integer> findQuantiteByDestinataireId(Integer id);
}
