package br.forsign.allo.common.utils;

/*
 * @author prandini
 * created 5/22/24
 */

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonExceptionMessages {

    private static final String NAO_ENCONTRADO_ID = "%s não encontrado(a) com id %s";

    public static String naoEncontrado(String nome, Long id){
        return String.format(NAO_ENCONTRADO_ID, nome, id);
    }
}
