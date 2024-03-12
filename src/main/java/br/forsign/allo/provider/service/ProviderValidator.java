package br.forsign.allo.provider.service;


import br.forsign.allo.provider.exception.ProviderException;
import br.forsign.allo.provider.exception.ProviderExceptionMessages;
import br.forsign.allo.provider.model.ProviderInputDTO;
import br.forsign.allo.user.domain.TipoPessoa;
import br.forsign.allo.user.service.actions.UserGetter;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProviderValidator {

    @Resource
    private UserGetter userGetter;
/*
    public void validarProviderCreate(ProviderInputDTO inputDTO){
        validarVinculoUsuarioExistente(inputDTO.getUserId());
    }
*/
    private void validarVinculoUsuarioExistente(Long userID){
        Optional.ofNullable(userGetter.getById(userID))
                .orElseThrow(() -> new ProviderException(ProviderExceptionMessages.usuarioNaoCadastrado()));
    }

    private void validarTipoPessoaBateDocumento(TipoPessoa tipoPessoa, Long userID){
        // ESSE CARA PECISA QUE O PRESTADOR TENHA UM DOCUMENTO
    }
}
