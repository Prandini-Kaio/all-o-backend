package br.forsign.allo.servico.model;

import br.forsign.allo.avaliacao.model.AvaliacaoInput;
import br.forsign.allo.avaliacao.model.AvaliacaoOutput;
import br.forsign.allo.cliente.model.ClienteOuput;
import br.forsign.allo.provedor.model.ProvedorOutput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@Data
public class ServicoInput {

    @NotNull
    private Long id;

    @Valid
    private AvaliacaoInput avaliacao;

}
