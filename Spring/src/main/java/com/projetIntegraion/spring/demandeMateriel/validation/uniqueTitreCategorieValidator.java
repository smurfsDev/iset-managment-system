package com.projetIntegraion.spring.demandeMateriel.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projetIntegraion.spring.demandeMateriel.annotation.uniqueTitreCategorie;
import com.projetIntegraion.spring.demandeMateriel.repository.CategorieMaterielRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class uniqueTitreCategorieValidator implements
        ConstraintValidator<uniqueTitreCategorie, String> {

    @Autowired
    private CategorieMaterielRepository categorieMaterielRepository;

    @Override
    public void initialize(uniqueTitreCategorie titre) {
    }

    @Override
    public boolean isValid(String titre,
            ConstraintValidatorContext cxt) {
        return !categorieMaterielRepository.existsByTitre(titre);
    }

}
