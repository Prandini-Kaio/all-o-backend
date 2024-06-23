package br.forsign.allo.auth.model;

import br.forsign.allo.usuario.domain.UsuarioRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 20/06/2024
 */
@Data
@AllArgsConstructor
public class LoginOutput {

    private Long id;

    private String nome;

    private UsuarioRole role;

    private String token;

}
