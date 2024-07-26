package br.forsign.allo.cliente.model;

import br.forsign.allo.auth.model.AuthInput;
import br.forsign.allo.entidade.model.EntidadeInput;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClienteInput extends EntidadeInput {

    @Schema(example = "Matheus Frangote", description = "Nome do cliente.")
    private String nome;

    private String imagem;
}
