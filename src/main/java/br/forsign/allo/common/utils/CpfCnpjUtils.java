package br.forsign.allo.common.utils;

import liquibase.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.InputMismatchException;

/**
 * @author kaiooliveira
 * created 26/02/2024
 */

public class CpfCnpjUtils {

    public static final int SIZE_CPF = 11;
    public static final int SIZE_CNPJ_13 = 13;
    public static final int SIZE_CNPJ_14 = 14;
    public static final int SIZE_CNPJ_15 = 15;

    public static boolean isCpf(String cpfCnpj){
        String cpfSemMascara = removeMascara(cpfCnpj);
        if(StringUtils.isEmpty(cpfSemMascara))
            return false;

        return cpfSemMascara.length() == SIZE_CPF;
    }

    public static boolean isCnpj(String cpfCnpj){
        String cnpjSemMascara = removeMascara(cpfCnpj);
        if(StringUtils.isEmpty(cnpjSemMascara))
            return false;

        return cnpjSemMascara.length() == SIZE_CNPJ_14;
    }

    public static boolean isCpfValido(String cpf){
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11))
            return (false);

            char digito10, digito11;
            int soma, numeroConvertido, peso, r;

            try{
                soma = 0;
                peso = 10;

                for(int i = 0; i < 9; i++){
                    // Converte o numero do index de string para um int subtraindo do caracter '0' da tab ACII
                    numeroConvertido = (int)(cpf.charAt(i) -48);
                    soma = soma +(numeroConvertido * peso);
                    peso = peso - 1;
                }

                r = 11 - (soma % 11);
                if((r == 10) || (r == 11))
                    digito10 = '0';
                else
                    digito10 = (char) (r + 48); // Converte no digito da tab ASCII

                soma = 0;
                peso = 11;

                for(int i = 0; i < 10; i++){
                    numeroConvertido = (int)(cpf.charAt(i)-48);
                    soma = soma + (numeroConvertido * peso);
                    peso = peso - 1;
                }

                r = 11 - (soma % 11);
                if((r == 10) || (r == 11))
                    digito11 = '0';
                else
                    digito11 = (char)(r + 48);

                return (digito10 == cpf.charAt(9)) && (digito11 == cpf.charAt(10));
            }catch (InputMismatchException e){
                return false;
            }
    }

    public static boolean isCnpjValido(String cnpj) {
        cnpj = cnpj.replace(".", "").replace("-", "").replace("/", "");
        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") ||
                cnpj.equals("22222222222222") || cnpj.equals("33333333333333") ||
                cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
                cnpj.equals("66666666666666") || cnpj.equals("77777777777777") ||
                cnpj.equals("88888888888888") || cnpj.equals("99999999999999") ||
                (cnpj.length() != 14))
            return (false);

        char dig13, dig14;
        int sm, i, r, num, peso;

        // "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                // converte o i-ésimo caractere do CNPJ em um número:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posição de '0' na tabela ASCII)
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else dig13 = (char) ((11 - r) + 48);

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else dig14 = (char) ((11 - r) + 48);

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
                return (true);
            else return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static boolean isCpfCnpjValido(String cpfCnpj){
        String cleanCpfCnpj = removeMascara(cpfCnpj);

        if(cleanCpfCnpj.length() == SIZE_CPF)
            return isCpfValido(cleanCpfCnpj);
        if(cleanCpfCnpj.length() == SIZE_CNPJ_14)
            return isCnpjValido(cpfCnpj);

        return false;
    }

    public static String aplicaMascara(String cpfCnpj){
        // Nao deve tratar um cpf que tenha algo alem dos algarismos.
        if(removeMascara(cpfCnpj).length() != cpfCnpj.length())
            return cpfCnpj;

        if(cpfCnpj.length() == SIZE_CPF)
            return mascarar("###.###.###-##", cpfCnpj);
        else if(cpfCnpj.length() == SIZE_CNPJ_13)
            return mascarar("#.###.###/####-##", cpfCnpj);
        else if(cpfCnpj.length() == SIZE_CNPJ_14)
            return mascarar("##.###.###/####-##", cpfCnpj);
        else if(cpfCnpj.length() == SIZE_CNPJ_15)
            return mascarar("###.###.###/####-##", cpfCnpj);
        else
            return cpfCnpj;
    }

    public static String removeMascara(String cpfCnpj){
        if(cpfCnpj.isEmpty() || cpfCnpj == null)
            return cpfCnpj;

        return cpfCnpj.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("/", "");
    }

    private static String mascarar(String mask, String cpfCnpj){
        String maskCpfCnpj = mask;
        for(int i = 0; i < cpfCnpj.length(); i++){
            maskCpfCnpj = maskCpfCnpj.replaceFirst("#", cpfCnpj.substring(i, i + 1));
        }

        return maskCpfCnpj;
    }
}
