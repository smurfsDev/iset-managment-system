package com.projetIntegraion.spring.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.projetIntegraion.spring.validation.uniqueValidator;


@Documented
@Constraint(validatedBy = uniqueValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface uniqueConstraint {
    String message() default "nom d'utilisateur déjà existant";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}