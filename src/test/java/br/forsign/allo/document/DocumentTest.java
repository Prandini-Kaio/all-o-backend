package br.forsign.allo.document;

import br.forsign.allo.common.utils.CpfCnpjUtils;
import br.forsign.allo.document.service.action.DocumentValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author kaiooliveira
 * created 26/02/2024
 */

public class DocumentTest {

    @Test
    public void testCpfFormatoValido(){
        DocumentValidator validator = new DocumentValidator();
        String cpf = "700.568.756-65";
        String cpfS = "70056875664";

        System.out.println(CpfCnpjUtils.aplicaMascara(cpfS));
        System.out.println(CpfCnpjUtils.removeMascara(cpf));

        Assertions.assertTrue(validator.validaCpfCnpj(cpf));
    }
}
