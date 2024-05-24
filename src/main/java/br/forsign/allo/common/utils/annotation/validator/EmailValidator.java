package br.forsign.allo.common.utils.annotation.validator;

import br.forsign.allo.common.utils.CommonExceptionMessages;
import br.forsign.allo.common.utils.ContatoUtils;
import br.forsign.allo.common.utils.annotation.Email;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;

public class EmailValidator implements ConstraintValidator<Email, String> {
    @Override
    public void initialize(Email constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if(!ContatoUtils.isEmailValido(email))
            throw new ValidationException(CommonExceptionMessages.inputInvalido("Email", email));
        return true;
    }
}