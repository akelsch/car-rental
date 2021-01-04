package de.htwsaar.prog3.carrental.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {MinAgeValidator.class})
public @interface MinAge {

    String message() default "de.htwsaar.prog3.carrental.model.validation.MinAge";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int value();
}
