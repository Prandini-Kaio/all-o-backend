package br.forsign.allo.servico.model;

import br.forsign.allo.avaliacao.model.AvaliacaoOutput;
import br.forsign.allo.cliente.model.ClienteOuput;
import br.forsign.allo.provedor.model.ProvedorOutput;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@Data
public class ServicoOutput {

    private Long id;

    private ProvedorOutput provedor;

    private ClienteOuput cliente;

    private AvaliacaoOutput avaliacao;

    private boolean servicoRealizado;

    private boolean visualizadoProvedor;

}
