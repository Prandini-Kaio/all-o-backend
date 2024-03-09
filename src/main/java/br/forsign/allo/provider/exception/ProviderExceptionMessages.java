package br.forsign.allo.provider.exception;

public class ProviderExceptionMessages {

    private static String USUARIO_NAO_CADASTRADO = "O usuario não foi encontrado em nossa base de dados.";

    public static String usuarioNaoCadastrado(){
        return USUARIO_NAO_CADASTRADO;
    }
}
