package br.forsign.allo.usuario.domain;

import lombok.Getter;

/**
 * @author kaiooliveira
 * created 20/06/2024
 */

@Getter
public enum UsuarioRole {
    PROVEDOR("provedor"),

    CLIENTE("cliente");

    private String role;

    UsuarioRole(String role){
        this.role = role;
    }
}
