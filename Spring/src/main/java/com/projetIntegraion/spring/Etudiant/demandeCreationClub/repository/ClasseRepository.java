package com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository;

import java.util.List;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Classe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
public interface ClasseRepository extends JpaRepository<Classe, Long>{
    Page<Classe> findByDepartementId(Long id,Pageable pageable);
    List<Classe> findByDepartementId(Long id);
    Boolean existsByNom(String nom);
    Boolean existsByAbreviation(String abreviation);

    @Query("select u , c,r from User u join UserRole r on u.id = r.user.id join Classe c on r.classe = c.id where r.departement = ?1")
    List<Object[]> findByDep(int id);

}
