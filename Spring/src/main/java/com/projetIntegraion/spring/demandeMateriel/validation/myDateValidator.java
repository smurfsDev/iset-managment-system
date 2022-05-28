package com.projetIntegraion.spring.demandeMateriel.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.projetIntegraion.spring.demandeMateriel.annotation.myDateConstraint;

import org.springframework.beans.BeanWrapperImpl;

public class myDateValidator implements ConstraintValidator<myDateConstraint, Object> {

    private String DateDeRemise;
    Date dr;
    private String DateDemploi;
    Date de;
    private String message;

    public void initialize(myDateConstraint constraintAnnotation) {
        this.DateDeRemise = constraintAnnotation.DateDeRemise();
        this.DateDemploi = constraintAnnotation.DateDemploi();
        this.message = constraintAnnotation.message();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        int isValid = 1;
        Object DateDeRemiseValue = new BeanWrapperImpl(value)
                .getPropertyValue(DateDeRemise);
        Object DateDemploiValue = new BeanWrapperImpl(value)
                .getPropertyValue(DateDemploi);

        // 1 jawna behi
        // 2 date emploi mch date
        // 3 date remise mch date
        // 4 date emploi passée
        // 5 date remise passée
        // 6 date remise ba3d date emploi

        // DATA REMISE MCH DATE
        try {
            dr = new SimpleDateFormat("yyyy-MM-dd").parse((String) DateDeRemiseValue);

        } catch (ParseException e) {
            isValid = 3;
            e.printStackTrace();
        }
        // DATE EMPLOI MCH DATE
        try {
            de = new SimpleDateFormat("yyyy-MM-dd").parse((String) DateDemploiValue);
        } catch (ParseException e) {
            isValid = 2;
            e.printStackTrace();
        }

        if (isValid != 2 && isValid != 3) {

            if (de.compareTo(dr) > 0) {
                isValid = 6;
            }
            // date remise passée
            if (dr.before(new Date())) {
                isValid = 5;
            }

            // date emploi passée
            if (de.before(new Date())) {
                isValid = 4;
            }
        }
        if (isValid != 1) {
            if (isValid == 6) {
                context.disableDefaultConstraintViolation();
                context
                        .buildConstraintViolationWithTemplate("Date remise doit etre apres date d'emploi!")
                        .addPropertyNode(DateDeRemise)
                        .addConstraintViolation();
                context
                        .buildConstraintViolationWithTemplate("Date remise doit etre apres date d'emploi!")
                        .addPropertyNode(DateDemploi)
                        .addConstraintViolation();
            }

            if (isValid == 3) {
                context.disableDefaultConstraintViolation();
                context
                        .buildConstraintViolationWithTemplate("Date non valide")
                        .addPropertyNode(DateDeRemise)
                        .addConstraintViolation();
            }
            if (isValid == 2) {
                context.disableDefaultConstraintViolation();
                context
                        .buildConstraintViolationWithTemplate("Date non valide")
                        .addPropertyNode(DateDemploi)
                        .addConstraintViolation();
            }
            if (isValid == 5) {
                context.disableDefaultConstraintViolation();
                context
                        .buildConstraintViolationWithTemplate("Date dépassée")
                        .addPropertyNode(DateDeRemise)
                        .addConstraintViolation();
            }
            if (isValid == 4) {
                context.disableDefaultConstraintViolation();
                context
                        .buildConstraintViolationWithTemplate("Date dépassée")
                        .addPropertyNode(DateDemploi)
                        .addConstraintViolation();
            }

        }
        return isValid != 1 ? false : true;
    }
}