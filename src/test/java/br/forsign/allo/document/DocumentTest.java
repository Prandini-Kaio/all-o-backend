package br.forsign.allo.document;

import br.forsign.allo.common.utils.CpfCnpjUtils;
import br.forsign.allo.user.service.actions.document.DocumentValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author kaiooliveira
 * created 26/02/2024
 */

public class DocumentTest {

    @Test
    public void testCpfFormatoValido(){
        DocumentValidator validator = new DocumentValidator();
        String cpf = "634.950.660-04";
        String cpfS = "63495066004";

        Assertions.assertTrue(CpfCnpjUtils.isCpfValido(cpf));
        Assertions.assertTrue(CpfCnpjUtils.isCpfValido(cpfS));

        Assertions.assertTrue(CpfCnpjUtils.isCpf(cpf));
        Assertions.assertTrue(CpfCnpjUtils.isCpf(cpfS));
    }

    @Test
    public void testCnpjFormatoValido(){
        DocumentValidator validator = new DocumentValidator();
        String cnpj = "11.133.930/0001-30";
        String cnpjS = "11133930000130";

        Assertions.assertTrue(CpfCnpjUtils.isCnpjValido(cnpj));
        Assertions.assertTrue(CpfCnpjUtils.isCnpjValido(cnpjS));

        Assertions.assertTrue(CpfCnpjUtils.isCnpj(cnpj));
        Assertions.assertTrue(CpfCnpjUtils.isCnpj(cnpjS));
    }
}
