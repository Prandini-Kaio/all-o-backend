package br.forsign.allo.avaliacao.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*
 * @author prandini
 * created 5/26/24
 */

@Data
public class AvaliacaoInput {

    private Long id;

    @NotNull
    private Long provedorId;

    @NotNull
    private Long clienteId;

    @NotNull
    private double nota;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;
}
