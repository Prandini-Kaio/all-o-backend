package br.forsign.allo.profissao.service.action;

/*
 * @author prandini
 * created 5/25/24
 */

import br.forsign.allo.common.error.BusinessException;
import br.forsign.allo.common.utils.CommonExceptionMessages;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.profissao.model.ProfissaoInput;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ProfissaoValidator {

    @Resource
    private ProfissaoGetter getter;

    public void validarCreate(ProfissaoInput input) {
        validaExistente(input);
    }

    public void validaUpdate(ProfissaoInput input) {
        validaExistente(input);
    }

    private void validaExistente(ProfissaoInput input){
        if(getter.existsByNome(input.getNome()))
            throw new BusinessException(CommonExceptionMessages.jaExistente("Profissão", input.getNome()));
    }
}
