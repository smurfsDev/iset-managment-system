package com.projetIntegraion.spring.Etudiant.demandeCreationClub.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.validation.UniqueClubValidator;


@Documented
@Constraint(validatedBy = UniqueClubValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface uniqueDccConstraint {
    String message() default "Nom club deja existant";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}