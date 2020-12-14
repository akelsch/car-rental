package de.htwsaar.prog3.carrental.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartBeforeEndValidator implements ConstraintValidator<ValidRental, de.htwsaar.prog3.carrental.model.Rental> {

  @Override
  public boolean isValid(de.htwsaar.prog3.carrental.model.Rental rental, ConstraintValidatorContext constraintValidatorContext) {
    return rental.getEnd().isAfter(rental.getStart());
  }
}
