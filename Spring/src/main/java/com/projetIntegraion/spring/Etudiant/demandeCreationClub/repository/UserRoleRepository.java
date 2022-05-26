package com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import groovy.cli.Option;

@EnableJpaRepositories

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    Optional<UserRole> findByRoleIdAndUserId(Long id, int userId);
    Optional<UserRole> findFirstByUserId(int userId);

    List<UserRole> findByUserIdInAndRoleId(Set<Integer> userId, Long roleId);
    Optional<UserRole> findByRoleIdAndDepartementAndStatus(Long roleId, int department, int status);

        
}
