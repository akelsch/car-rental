package de.htwsaar.prog3.carrental.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class MinAgeValidator implements ConstraintValidator<MinAge, LocalDate> {

    private int minimalAge;

    @Override
    public void initialize(MinAge minAge) {
        this.minimalAge = minAge.value();
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate != null && localDate.isBefore(LocalDate.now().minusYears(minimalAge));
    }
}
