package de.htwsaar.prog3.carrental.model.validation;

import de.htwsaar.prog3.carrental.model.Rental;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartBeforeEndValidator implements ConstraintValidator<ValidRental, Rental> {

    @Override
    public boolean isValid(Rental rental, ConstraintValidatorContext constraintValidatorContext) {
        return rental.getEnd().isAfter(rental.getStart());
    }
}
