package br.forsign.allo.common.utils.annotation.validator;

import br.forsign.allo.common.utils.ContatoUtils;
import br.forsign.allo.common.utils.annotation.Email;
import br.forsign.allo.user.exceptions.DocumentException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {
    @Override
    public void initialize(Email constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if(!ContatoUtils.isEmailValido(email))
            throw new DocumentException("Endereço de e-mail inválido.");
        return true;
    }
}
