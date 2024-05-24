package br.forsign.allo.cliente.service.actions.document;

import br.forsign.allo.common.utils.CpfCnpjUtils;
import br.forsign.allo.cliente.exception.DocumentException;
import br.forsign.allo.cliente.exception.DocumentExceptionMessages;
import br.forsign.allo.cliente.model.document.DocumentInputDTO;
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