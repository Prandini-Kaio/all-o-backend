package br.forsign.allo.common.utils;

/*
 * @author prandini
 * created 5/22/24
 */

import br.forsign.allo.common.error.BusinessException;
import lombok.experimental.UtilityClass;

import java.util.function.Supplier;

@UtilityClass
public class CommonExceptionSupplier {

    public Supplier<BusinessException> naoEncontrado(String nome, Long id){
        return () -> new BusinessException(CommonExceptionMessages.naoEncontrado(nome, id));
    }
}
