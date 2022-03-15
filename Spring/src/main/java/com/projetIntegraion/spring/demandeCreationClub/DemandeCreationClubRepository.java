package com.projetIntegraion.spring.demandeCreationClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
public interface DemandeCreationClubRepository extends JpaRepository<DemandeCreationClub, Long>{
}
