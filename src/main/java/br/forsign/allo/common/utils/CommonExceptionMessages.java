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

    private static final String DOCUMENTO_JA_CADASTRADO = "O documento %s, já se encontra cadastrado em nossa base.";

    public static String naoEncontrado(String nome, Long id){
        return String.format(NAO_ENCONTRADO_ID, nome, id);
    }

    public static String naoEncontrado(String nome, String cpfCnpj){
        return String.format(NAO_ENCONTRADO_CPFCNPJ, nome, cpfCnpj);
    }

    public static String documentoJaCadastrado(String cpfCnpj){
        return String.format(DOCUMENTO_JA_CADASTRADO, cpfCnpj);
    }
}
