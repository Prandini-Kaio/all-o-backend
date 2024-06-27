package br.forsign.allo.provedor.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Data
@NoArgsConstructor
public class NotificacaoProvedorInput {

    private Long id;

    private String mensagem;

    private String nomeCliente;

    private Long idProvedor;

    private boolean visualizada;

    public NotificacaoProvedorInput(String mensagem){
        this.mensagem = mensagem;
        this.visualizada = false;
    }
}
