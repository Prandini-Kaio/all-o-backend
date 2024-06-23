package br.forsign.allo.cliente.model;

import br.forsign.allo.auth.model.AuthInput;
import jakarta.validation.Valid;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@Data
public class ClienteCadastroInput {

    @Valid
    private AuthInput usuario;

    @Valid
    private ClienteInput cliente;
}
