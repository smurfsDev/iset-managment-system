package com.projetIntegraion.spring.Etudiant.demandeCreationClub.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.annotation.uniqueDccConstraint;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.DemandeCreationClubService;

import org.springframework.beans.factory.annotation.Autowired;

public class UniqueClubValidator implements 
  ConstraintValidator<uniqueDccConstraint, String> {

    @Autowired
    private DemandeCreationClubService demandeCreationClubService;
    @Override
    public void initialize(uniqueDccConstraint nomClub) {
    }

    @Override
    public boolean isValid(String nomClub,
      ConstraintValidatorContext cxt) {
        System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
        System.out.println("nomClub : " + nomClub);
        return !demandeCreationClubService.dccexists(nomClub);
    }

}