package com.projetIntegraion.spring.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.annotation.uniqueConstraint;

import org.springframework.beans.factory.annotation.Autowired;

public class uniqueValidator implements ConstraintValidator<uniqueConstraint, String> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void initialize(uniqueConstraint uniqueConstraint) {
    }

    @Override
    public boolean isValid(String username,
      ConstraintValidatorContext cxt) {
        return !userRepository.findUserWithName(username).isPresent();
    }
}