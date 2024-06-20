package br.forsign.allo.auth.model;

import br.forsign.allo.usuario.domain.UsuarioRole;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 20/06/2024
 */

public record RegisterInput(String login, String senha, UsuarioRole role) {
}
