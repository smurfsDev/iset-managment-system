package com.projetIntegraion.spring.Etudiant.demandeCreationClub.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.annotation.uniqueClassName;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.ClasseRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class uniqueClassNameValidator implements 
  ConstraintValidator<uniqueClassName, String> {

    @Autowired
    private ClasseRepository classeRepository;
    @Override
    public void initialize(uniqueClassName nomClub) {
    }

    @Override
    public boolean isValid(String nomClub,
      ConstraintValidatorContext cxt) {
        return !classeRepository.existsByNom(nomClub);
    }

}