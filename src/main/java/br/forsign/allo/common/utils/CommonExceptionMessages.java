package br.forsign.allo.common.utils;

/*
 * @author prandini
 * created 5/22/24
 */

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonExceptionMessages {

    private static final String NAO_ENCONTRADO_ID = "%s não encontrado(a) com id %s.";

    private static final String NAO_ENCONTRADO_CPFCNPJ = "%s não encontrado(a) com CPF/CNPJ %s.";

    private static final String DOCUMENTO_JA_CADASTRADO = "%s com documento %s já cadastrado.";

    private static final String INPUT_INVALIDO = "O campo \"%s\" com valor \"%s\" inválido.";

    public static String naoEncontrado(String nome, Long id){
        return String.format(NAO_ENCONTRADO_ID, nome, id);
    }

    public static String naoEncontrado(String nome, String cpfCnpj){
        return String.format(NAO_ENCONTRADO_CPFCNPJ, nome, cpfCnpj);
    }

    public static String documentoJaCadastrado(String nome, String cpfCnpj){
        return String.format(DOCUMENTO_JA_CADASTRADO, nome, cpfCnpj);
    }

    public static String inputInvalido(String campo, String input){
        return String.format(INPUT_INVALIDO, campo, input);
    }
}
