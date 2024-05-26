package br.forsign.allo.avaliacao.model;

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.provedor.domain.Provedor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*
 * @author prandini
 * created 5/26/24
 */

@Data
public class AvaliacaoOutput {

    private Long id;

    private Provedor provedor;

    private Cliente cliente;

    private double nota;

    private String titulo;

    private String descricao;
}
