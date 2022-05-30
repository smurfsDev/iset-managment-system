package com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Classe;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.UserRole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    Optional<UserRole> findByRoleIdAndUserId(Long id, int userId);
    Optional<UserRole> findFirstByUserId(int userId);

    List<UserRole> findByUserIdInAndRoleId(Set<Integer> userId, Long roleId);
    List<UserRole> findByUserIdInAndRoleIdAndDepartement(Set<Integer> userId, Long roleId, int departmentId);
    List<UserRole> findByUserIdInAndRoleIdAndDepartement(Set<Integer> userId, Long roleId, int departmentId,Pageable pageable);
    Optional<UserRole> findByRoleIdAndDepartementAndStatus(Long roleId, int department, int status);

    @Query("select u ,r,d from User u join UserRole r on u.id = r.user.id join Departement d on d.id = r.departement where r.role.name = 'ROLE_CHEFDEPARTEMENT'")
    Page<Object[]> findAllChefDepartments(Pageable pageable);

    @Query("select u , d,r from User u join UserRole r on u.id = r.user.id join Departement d on r.departement = d.id where r.departement = ?1 and r.role.name = 'ROLE_ENSEIGNANT'")
    Page<Object[]> findByDepAndRoleEnseignant(int id,Pageable pageable);

    // @Query("select u,c,m from UserRole u join Classe c on u.classe.id = c.id join Matiere m on m.classe.id = c.id where u.user.id = ?1")
    // Page<Object[]> findAllClassesParEnseignant( int userId, Pageable pageable);

    // @Query("select u ,r from User u join UserRole r on u.id = r.user.id where r.role.name = 'ROLE_ENSEIGNANT' & r.status = 1")
    // List<Object[]> findAllApprovedEnseignants();

    Optional<UserRole> findByRoleIdAndStatus(Long roleId, int status);
    
    // Optional<UserRole> findByClasseId(Long classe);

    




        
}
