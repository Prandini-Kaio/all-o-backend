package br.forsign.allo.common.utils.annotation;

import br.forsign.allo.common.utils.annotation.validator.CpfCnpjValidator;
import br.forsign.allo.cliente.domain.TipoPessoa;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {CpfCnpjValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfCnpj {
    String message() default "CPF/CNPJ inválido";
    TipoPessoa tipoDocumento();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
