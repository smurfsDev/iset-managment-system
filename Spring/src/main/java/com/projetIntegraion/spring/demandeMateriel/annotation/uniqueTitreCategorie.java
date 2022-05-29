package com.projetIntegraion.spring.demandeMateriel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

import com.projetIntegraion.spring.demandeMateriel.validation.uniqueTitreCategorieValidator;

@Documented
@Constraint(validatedBy = uniqueTitreCategorieValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface uniqueTitreCategorie {
    String message() default "Categorie déja existante";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
