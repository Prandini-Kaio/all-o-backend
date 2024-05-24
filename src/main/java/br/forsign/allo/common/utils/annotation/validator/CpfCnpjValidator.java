package br.forsign.allo.common.utils.annotation.validator;

import br.forsign.allo.common.utils.CommonExceptionMessages;
import br.forsign.allo.common.utils.CpfCnpjUtils;
import br.forsign.allo.common.utils.annotation.CpfCnpj;
import br.forsign.allo.entidade.model.TipoPessoaEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;

public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, String> {
    private TipoPessoaEnum tipoDocumento;

    @Override
    public void initialize(CpfCnpj constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.tipoDocumento = constraintAnnotation.tipoDocumento();
    }

    @Override
    public boolean isValid(String cpfCnpj, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = false;
        String cpfCnpjSMask = CpfCnpjUtils.removeMascara(cpfCnpj);

        if(this.tipoDocumento == TipoPessoaEnum.FISICA)
            valid = CpfCnpjUtils.isCpfValido(cpfCnpjSMask);
        else if(this.tipoDocumento == TipoPessoaEnum.JURIDICA)
            valid = CpfCnpjUtils.isCnpjValido(cpfCnpjSMask);
        else if (this.tipoDocumento == TipoPessoaEnum.FISICA_JURIDICA)
            valid = CpfCnpjUtils.isCpfCnpjValido(cpfCnpjSMask);

        if(!valid)
            throw new ValidationException(CommonExceptionMessages.inputInvalido("CPF/CNPJ", cpfCnpj));

        return valid;
    }
}
