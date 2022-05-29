package com.projetIntegraion.spring.demandeMateriel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

import com.projetIntegraion.spring.demandeMateriel.validation.uniqueTitreValidator;

@Documented
@Constraint(validatedBy = uniqueTitreValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface uniqueTitre {
    String message() default "Materiel deja existant";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
