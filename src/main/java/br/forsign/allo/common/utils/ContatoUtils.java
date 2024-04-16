package br.forsign.allo.common.utils;

public class ContatoUtils {

    public static boolean isEmailValido(String email){
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }
}
