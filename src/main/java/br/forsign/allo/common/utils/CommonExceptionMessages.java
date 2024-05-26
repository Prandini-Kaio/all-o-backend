package br.forsign.allo.common.utils;

/*
 * @author prandini
 * created 5/22/24
 */

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonExceptionMessages {

    private static final String NAO_ENCONTRADO = "%s não encontrado(a).";

    private static final String NAO_ENCONTRADO_ID = "%s não encontrado(a) com id %s.";

    private static final String NAO_ENCONTRADO_CPFCNPJ = "%s não encontrado(a) com CPF/CNPJ %s.";

    private static final String ENTIDADE_JA_CADASTRADA = "%s com documento %s já cadastrado.";

    private static final String CAMPO_INVALIDO = "O campo \"%s\" com valor \"%s\" inválido.";

    private static final String JA_EXISTENTE = "\"%s\" com valor \"%s\" já cadastrado.";


    public static String naoEncontrado(String nome){
        return String.format(NAO_ENCONTRADO, nome);
    }

    public static String naoEncontrado(String nome, Long id){
        return String.format(NAO_ENCONTRADO_ID, nome, id);
    }

    public static String naoEncontrado(String nome, String cpfCnpj){
        return String.format(NAO_ENCONTRADO_CPFCNPJ, nome, cpfCnpj);
    }

    public static String entidadeJaCadastrada(String nome, String cpfCnpj){
        return String.format(ENTIDADE_JA_CADASTRADA, nome, cpfCnpj);
    }

    public static String campoInvalido(String campo, String input){
        return String.format(CAMPO_INVALIDO, campo, input);
    }

    public static String jaExistente(String campo, String valor){
        return String.format(JA_EXISTENTE, campo, valor);
    }
}
