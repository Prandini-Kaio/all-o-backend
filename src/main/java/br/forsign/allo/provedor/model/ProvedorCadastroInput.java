package br.forsign.allo.provedor.model;

import br.forsign.allo.auth.model.AuthInput;
import jakarta.validation.Valid;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@Data
public class ProvedorCadastroInput {

    @Valid
    private AuthInput usuario;

    @Valid
    private ProvedorInput provedor;
}
