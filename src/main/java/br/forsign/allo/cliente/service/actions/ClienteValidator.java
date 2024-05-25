package br.forsign.allo.cliente.service.actions;

import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.repository.ClienteRepository;
import br.forsign.allo.common.error.BusinessException;
import br.forsign.allo.common.utils.CommonExceptionMessages;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ClienteValidator {

    @Resource
    private ClienteRepository repository;

    @Resource
    private ClienteGetter getter;

    public void validarCreate(ClienteInput input){
        validarDocumentoJaCadastrado(input);
    }

    public void validarUpdate(ClienteInput input){
        validarExistente(input.getId());
        validarDocumentoJaCadastrado(input);
    }

    private void validarExistente(Long id){
        if(!getter.existsById(id))
            throw new BusinessException(CommonExceptionMessages.naoEncontrado("Cliente", id));
    }

    private void validarDocumentoJaCadastrado(ClienteInput input){
        if(getter.existsByCpf(input.getCpfCnpj())){
            if(getter.byCpf(input.getCpfCnpj()).getId() != input.getId())
                throw new BusinessException(CommonExceptionMessages.entidadeJaCadastrada("Cliente", input.getCpfCnpj()));
        }
    }
}