package br.forsign.allo.avaliacao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/*
 * @author prandini
 * created 5/26/24
 */

@Data
public class AvaliacaoInput {

    @Schema(example = "1", description = "Identificador único da avaliação.")
    private Long id;

    @NotNull
    @Schema(example = "5.0", description = "Nota pelo serviço prestado.")
    private double qualidade;

    @NotNull
    @Schema(example = "5.0", description = "Nota pela agilidade do serviço.")
    private double agilidade;

    @NotNull
    @Schema(example = "5.0", description = "Nota pelo valor cobrado do serviço.")
    private double preco;

    @NotBlank
    @Schema(description = "Descrição/comentário da avaliação.")
    private String descricao;

    private List<String> uriImagens;
}
