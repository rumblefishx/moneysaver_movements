package com.rumblesoftware.mv.io.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MoneyInStringValidation.class)
public @interface MoneyInStringVal {
	int decimals() default 2;
    String message() default "movement.add.amount.not.valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}