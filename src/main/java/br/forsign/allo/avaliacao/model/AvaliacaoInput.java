package br.forsign.allo.avaliacao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*
 * @author prandini
 * created 5/26/24
 */

@Data
public class AvaliacaoInput {

    @Schema(example = "1", description = "Identificador único da avaliação.")
    private Long id;

    @NotNull
    @Schema(example = "5.0", description = "Nota do serviço para o provedor.")
    private double nota;

    @NotBlank
    @Schema(description = "Descrição/comentário da avaliação.")
    private String descricao;
}
