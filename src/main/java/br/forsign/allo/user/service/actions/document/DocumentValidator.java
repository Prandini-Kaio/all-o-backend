package br.forsign.allo.user.service.actions.document;

import br.forsign.allo.common.utils.CpfCnpjUtils;
import br.forsign.allo.user.exception.DocumentException;
import br.forsign.allo.user.exception.DocumentExceptionMessages;
import br.forsign.allo.user.model.document.DocumentInputDTO;
import org.springframework.stereotype.Component;

/**
 * @author kaiooliveira
 * created 26/02/2024
 */

@Component
public class DocumentValidator {

    public void validarDocumentos(DocumentInputDTO inputDTO){
        validarCpfCnpj(inputDTO.getCpfCnpj());
    }

    public void validarDocumentos(String cpfCnpj){
        validarDocumentos(new DocumentInputDTO(cpfCnpj));
    }

    public void validarCpfCnpj(String cpfCnpj){
        if(!CpfCnpjUtils.isCpfCnpjValido(cpfCnpj))
            throw new DocumentException(DocumentExceptionMessages.cpfCnpjInvalido(cpfCnpj));
    }
}