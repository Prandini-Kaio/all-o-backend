package br.forsign.allo.common.utils.annotation;

import br.forsign.allo.common.utils.annotation.validator.CpfCnpjValidator;
import br.forsign.allo.entidade.model.TipoPessoaEnum;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {CpfCnpjValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfCnpj {
    String message() default "CPF/CNPJ inválido";
    TipoPessoaEnum tipoDocumento();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
