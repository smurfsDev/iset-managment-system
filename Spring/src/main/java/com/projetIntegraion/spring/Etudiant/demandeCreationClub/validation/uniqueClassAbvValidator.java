package com.projetIntegraion.spring.Etudiant.demandeCreationClub.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.annotation.uniqueClassAbv;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.ClasseRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class uniqueClassAbvValidator implements 
  ConstraintValidator<uniqueClassAbv, String> {

    @Autowired
    private ClasseRepository classeRepository;
    @Override
    public void initialize(uniqueClassAbv nomClub) {
    }

    @Override
    public boolean isValid(String nomClub,
      ConstraintValidatorContext cxt) {
        return !classeRepository.existsByAbreviation(nomClub);
    }

}