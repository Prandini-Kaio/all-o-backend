package br.forsign.allo.cliente.exception;

public class DocumentExceptionMessages {

    private static String CPF_CNPJ_INVALIDO = "CPF ou CNPJ inválido.";
    private static String USER_JA_CADASTRADO = "Usuario com documento %s já cadastrado no sistema.";

    public static String cpfCnpjInvalido(String document){
        return String.format(CPF_CNPJ_INVALIDO, document);
    }
    public static String userJaCadastrado(String cpfCnpj){
        return String.format(USER_JA_CADASTRADO, cpfCnpj);
    }
}
