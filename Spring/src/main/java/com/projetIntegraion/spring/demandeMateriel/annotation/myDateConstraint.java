package com.projetIntegraion.spring.demandeMateriel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.projetIntegraion.spring.demandeMateriel.validation.myDateValidator;

@Documented
@Constraint(validatedBy = { myDateValidator.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface myDateConstraint {
    String message() default "Date remise doit etre apres date d'emploi";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String DateDemploi();

    String DateDeRemise();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        myDateConstraint[] value();
    }
}