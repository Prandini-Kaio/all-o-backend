package br.forsign.allo.document;

import br.forsign.allo.common.utils.CpfCnpjUtils;
import br.forsign.allo.user.exception.DocumentException;
import br.forsign.allo.user.service.actions.document.DocumentValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kaiooliveira
 * created 26/02/2024
 */

public class DocumentTest {

    private final String CPF = "634.950.660-04";
    private final String CPF_SEM_MASCARA = "63495066004";

    private final String CNPJ = "11.133.930/0001-30";
    private final String CNPJ_SEM_MASCARA = "11133930000130";

    private final String CPF_INVALIDO = "634.950.660-00";
    private final String CNPJ_INVALIDO = "11.133.930/0001-00";

    @Test
    public void testCpfFormatoValido(){
        // VERIFICA SE EH UM CPF
        Assertions.assertTrue(CpfCnpjUtils.isCpf(CPF));
        Assertions.assertTrue(CpfCnpjUtils.isCpf(CPF_SEM_MASCARA));
        // VERIFICA SE O CPF EH VALIDO
        Assertions.assertTrue(CpfCnpjUtils.isCpfValido(CPF));
        Assertions.assertTrue(CpfCnpjUtils.isCpfValido(CPF_SEM_MASCARA));

        // VERIFICA SE EH UM CNPJ
        Assertions.assertTrue(CpfCnpjUtils.isCnpj(CNPJ));
        Assertions.assertTrue(CpfCnpjUtils.isCnpj(CNPJ_SEM_MASCARA));
        // VERIFICA SE O CNPJ EH VALIDO
        Assertions.assertTrue(CpfCnpjUtils.isCnpjValido(CNPJ));
        Assertions.assertTrue(CpfCnpjUtils.isCnpjValido(CNPJ_SEM_MASCARA));
    }

    @Test
    public void testCpfCnpjInvalido(){
        DocumentValidator documentValidator = new DocumentValidator();

        Assertions.assertThrows(DocumentException.class, () -> documentValidator.validarCpfCnpj(CPF_INVALIDO));
        Assertions.assertThrows(DocumentException.class, () -> documentValidator.validarCpfCnpj(CNPJ_INVALIDO));
    }
}