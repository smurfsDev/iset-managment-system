package com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(" select u from User u where u.username = ?1")
	Optional<User> findUserWithName(String username);
    void deleteById(int id);
    Page<User> findByRolesId(long l, Pageable pageable);

    List<User> findByRolesId(long l);
}
