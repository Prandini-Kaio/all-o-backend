package br.forsign.allo.importacao.exception;

/*
 * @author prandini
 * created 10/6/24
 */
public class FileStorageExceptionMessages {

    private static final String NAO_ENCONTRADO = "Arquivo informado não encontrado.";

    private static final String ERRO_LENDO_ARQUIVO = "Erro lendo arquivo \"%s\": %s";

    public static final String ERRO_CRIANDO_ARQUIVO = "Erro criando arquivo: %s";

    public static final String ERRO_CARREGANDO_LOGO = "Erro carregando logo.";

    public static String naoEncontrado(){
        return NAO_ENCONTRADO;
    }

    public static String erroLendoArquivo(String originalFilename, String message) {
        return String.format(ERRO_LENDO_ARQUIVO, originalFilename, message);
    }

    public static String erroCriandoArquivo(String message) {
        return String.format(ERRO_CRIANDO_ARQUIVO, message);
    }

    public static String erroCarregandoLogo() {
        return String.format(ERRO_CARREGANDO_LOGO);
    }
}
