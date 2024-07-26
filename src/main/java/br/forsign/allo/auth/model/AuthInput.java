package br.forsign.allo.auth.model;

import br.forsign.allo.common.utils.annotation.Email;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.usuario.domain.UsuarioRole;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 20/06/2024
 */

@Data
public class AuthInput {

    @Email
    private String login;

    @Size(min = 8, message = "O tamanho minímo da senha é de 8 caracteres.")
    private String senha;

    private UsuarioRole role;

}
