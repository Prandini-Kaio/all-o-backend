package br.forsign.allo.servico.service.action;

import br.forsign.allo.avaliacao.service.action.AvaliacaoGetter;
import br.forsign.allo.common.error.BusinessException;
import br.forsign.allo.common.utils.CommonExceptionMessages;
import br.forsign.allo.provedor.service.ProvedorValidator;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import br.forsign.allo.servico.exception.ServicoExceptionMessages;
import br.forsign.allo.servico.model.ServicoInput;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/*
 * @author prandini
 * created 10/29/24
 */

@Component
public class ServicoValidator {

    @Resource
    private AvaliacaoGetter avaliacaoGetter;

    @Resource
    private ProvedorGetter provedorGetter;

    public void validar(ServicoInput input){
//        this.validarAvaliacao(input);
    }

    private void validarAvaliacao(ServicoInput input) {
        this.validarServicoJaAvaliado(input);
    }

    private void validarServicoJaAvaliado(ServicoInput input) {
        if(!this.avaliacaoGetter.existsByServico(input.getId())){
            throw new BusinessException(ServicoExceptionMessages.JA_AVALIADO());
        }
    }


}
