package de.htwsaar.prog3.carrental.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {StartBeforeEndValidator.class})
public @interface ValidRental {

    String message() default "de.htwsaar.prog3.carrental.model.validation.ValidRental";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
