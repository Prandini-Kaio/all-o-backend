package br.forsign.allo.user.service.actions.document;

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
