package br.forsign.allo.auth.model;

import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.usuario.domain.UsuarioRole;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 20/06/2024
 */

@Data
public class AuthInput {

    private String login;

    private String senha;

    private UsuarioRole role;

}
