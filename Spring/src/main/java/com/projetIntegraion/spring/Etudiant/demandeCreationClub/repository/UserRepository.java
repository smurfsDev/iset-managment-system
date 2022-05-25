package com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository;

import java.util.Optional;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(" select u from User u where u.username = ?1")
	Optional<User> findUserWithName(String username);
    void deleteById(int id);
}
