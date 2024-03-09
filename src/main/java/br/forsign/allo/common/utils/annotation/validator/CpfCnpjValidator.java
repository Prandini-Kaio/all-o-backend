package br.forsign.allo.common.utils.annotation.validator;

import br.forsign.allo.common.utils.annotation.CpfCnpj;
import br.forsign.allo.common.utils.CpfCnpjUtils;
import br.forsign.allo.user.domain.TipoPessoa;
import br.forsign.allo.user.exceptions.DocumentException;
import br.forsign.allo.user.exceptions.DocumentExceptionMessages;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, String> {
    private TipoPessoa tipoDocumento;

    @Override
    public void initialize(CpfCnpj constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.tipoDocumento = constraintAnnotation.tipoDocumento();
    }

    @Override
    public boolean isValid(String cpfCnpj, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = false;
        String cpfCnpjSMask = CpfCnpjUtils.removeMascara(cpfCnpj);

        if(this.tipoDocumento == TipoPessoa.FISICA)
            valid = CpfCnpjUtils.isCpfValido(cpfCnpjSMask);
        else if(this.tipoDocumento == TipoPessoa.JURIDICA)
            valid = CpfCnpjUtils.isCnpjValido(cpfCnpjSMask);
        else if (this.tipoDocumento == TipoPessoa.FISICA_JURIDICA)
            valid = CpfCnpjUtils.isCpfCnpjValido(cpfCnpjSMask);

        if(!valid)
            throw new DocumentException(DocumentExceptionMessages.cpfCnpjInvalido(cpfCnpjSMask));

        return valid;
    }
}
