package br.forsign.allo.provedor.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Data
public class NotificacaoProvedorInput {

    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensagem;

    @NotNull
    private Long idProvedor;

    private boolean visualizada;
}
