package br.forsign.allo.auth.model;

import br.forsign.allo.usuario.domain.UsuarioRole;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 20/06/2024
 */
@Data
@AllArgsConstructor
public class LoginOutput {

    private String token;

    private String nome;

    private Long id;

    private UsuarioRole role;

}
