package br.forsign.allo.document.service.action;

import br.forsign.allo.common.utils.CpfCnpjUtils;
import org.springframework.stereotype.Component;

/**
 * @author kaiooliveira
 * created 26/02/2024
 */

@Component
public class DocumentValidator {

    public boolean validaCpfCnpj(String cpfCnpj){
        return CpfCnpjUtils.isCpfCnpjValido(cpfCnpj);
    }
}
