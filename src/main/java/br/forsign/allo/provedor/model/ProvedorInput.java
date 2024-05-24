package br.forsign.allo.provedor.model;


import br.forsign.allo.entidade.model.EntidadeInput;
import br.forsign.allo.cliente.domain.TipoPessoa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProvedorInput extends EntidadeInput{

    @NotBlank
    @Schema(title = "Razao Social", example = "Douglas Mecanico")
    private String razaoSocial;

    @NotNull
    @Schema(title = "Tipo Pessoa", example = "FISICA", description = "Tipo de pessoa a ser cadastrada no sistema (FISICA/JURIDICA)")
    private TipoPessoa tipoPessoa;
}