package com.projetIntegraion.spring.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.projetIntegraion.spring.validation.EqualPasswordsValidator;


@Documented
@Constraint(validatedBy = { EqualPasswordsValidator.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface equalPasswordsConstraint {
    String message() default "Les mots de passe ne sont pas identiques";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    String field();

    String fieldMatch();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        equalPasswordsConstraint[] value();
    }
}