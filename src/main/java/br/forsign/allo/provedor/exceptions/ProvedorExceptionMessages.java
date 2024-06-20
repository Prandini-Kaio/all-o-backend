package br.forsign.allo.provedor.exceptions;

/**
 * @author kaiooliveira
 * created 20/06/2024
 */


public class ProvedorExceptionMessages {

    private static String TIPO_DOCUMENTO_INVALIDO = "Pessoa do tipo %s com documento %s inválido";

    public static String tipoDocumentoInvalido(String tipoPessoa, String documento){
        return String.format(TIPO_DOCUMENTO_INVALIDO, tipoPessoa, documento);
    }
}
