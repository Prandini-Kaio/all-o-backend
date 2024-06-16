package br.forsign.allo.provedor.service;


import br.forsign.allo.common.error.BusinessException;
import br.forsign.allo.common.utils.CommonExceptionMessages;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
public class ProvedorValidator {

    @Resource
    private ProvedorGetter getter;

    public void validarCreate(ProvedorInput inputDTO){
        validarDocumentoJaCadastrado(inputDTO);
    }

    public void validarUpdate(ProvedorInput input) {
        validarExistente(input.getId());
        validarDocumentoJaCadastrado(input);
    }

    private void validarExistente(Long id){
        if(!getter.existsById(id))
            throw new BusinessException(CommonExceptionMessages.naoEncontrado("Provedor", id));
    }

    private void validarDocumentoJaCadastrado(ProvedorInput input){
        if(getter.existsByCpfCnpj(input.getCpfCnpj())){
            if(getter.byCpfCnpj(input.getCpfCnpj()).getId() != input.getId())
                throw new BusinessException(CommonExceptionMessages.entidadeJaCadastrada("Provedor", input.getCpfCnpj()));
        }
    }
}
