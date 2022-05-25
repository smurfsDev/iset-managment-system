package com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository;

import java.util.List;
import java.util.Optional;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String role);
    Optional<Role> findById(Long id);
    List<Role> findAll();
    void deleteById(Long id);
}
