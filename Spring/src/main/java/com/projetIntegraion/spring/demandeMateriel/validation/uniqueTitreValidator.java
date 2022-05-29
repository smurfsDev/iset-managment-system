package com.projetIntegraion.spring.demandeMateriel.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projetIntegraion.spring.demandeMateriel.annotation.uniqueTitre;
import com.projetIntegraion.spring.demandeMateriel.repository.MaterielRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class uniqueTitreValidator implements
        ConstraintValidator<uniqueTitre, String> {

    @Autowired
    private MaterielRepository MaterielRepository;

    @Override
    public void initialize(uniqueTitre titre) {
    }

    @Override
    public boolean isValid(String titre,
            ConstraintValidatorContext cxt) {
        return !MaterielRepository.existsByTitre(titre);
    }

}
