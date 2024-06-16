package br.forsign.allo.profissao.model;

/*
 * @author prandini
 * created 5/25/24
 */

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProfissaoInput {

    @NotBlank
    @Schema(title = "Nome do icone", example = "medic-outline", description = "Nome de referência do ícone da profissão.")
    private String nomeIcone;

    @NotBlank
    @Schema(title = "Nome", example = "Médico", description = "Nome de referência da profissão.")
    private String nome;

}
