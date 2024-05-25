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
    @Schema(title = "Nome", example = "Médico", description = "Nome de referência da profissão.")
    private String nome;

    @NotBlank
    @Schema(title = "Descrição", example = "Profissional de saúde", description = "Descrição de referência da profissão.")
    private String descricao;

    @NotNull
    @Schema(title = "Categoria", example = "SAUDE", description = "Categoria geral de referência da profissão.")
    private ProfissaoCategoriaEnum categoria;
}
