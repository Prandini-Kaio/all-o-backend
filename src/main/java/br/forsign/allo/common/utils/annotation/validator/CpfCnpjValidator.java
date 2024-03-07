package br.forsign.allo.common.utils.annotation.validator;

import br.forsign.allo.common.utils.annotation.CpfCnpj;
import br.forsign.allo.common.utils.CpfCnpjUtils;
import br.forsign.allo.user.domain.TipoDocumento;
import br.forsign.allo.user.exceptions.DocumentException;
import br.forsign.allo.user.exceptions.DocumentExceptionMessages;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, String> {
    private TipoDocumento tipoDocumento;

    @Override
    public void initialize(CpfCnpj constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.tipoDocumento = constraintAnnotation.tipoDocumento();
    }

    @Override
    public boolean isValid(String cpfCnpj, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = false;
        String cpfCnpjSMask = CpfCnpjUtils.removeMascara(cpfCnpj);

        if(this.tipoDocumento == TipoDocumento.CPF)
            valid = CpfCnpjUtils.isCpfValido(cpfCnpjSMask);
        else if(this.tipoDocumento == TipoDocumento.CNPJ)
            valid = CpfCnpjUtils.isCnpjValido(cpfCnpjSMask);
        else if (this.tipoDocumento == TipoDocumento.CPFCNPJ)
            valid = CpfCnpjUtils.isCpfCnpjValido(cpfCnpjSMask);

        if(!valid)
            throw new DocumentException(DocumentExceptionMessages.cpfCnpjInvalido(cpfCnpjSMask));

        return valid;
    }
}
