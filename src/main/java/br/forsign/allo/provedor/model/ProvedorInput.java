package br.forsign.allo.provedor.model;


import br.forsign.allo.entidade.model.EntidadeInput;
import br.forsign.allo.entidade.model.TipoPessoaEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ProvedorInput extends EntidadeInput {

    @NotBlank
    @Schema(title = "Razão Social", example = "Douglas Mecanico", description = "Razão social do provedor de serviços.")
    private String razaoSocial;

    @Schema(title = "Tipo Pessoa", example = "FISICA", description = "Tipo de pessoa a ser cadastrada no sistema (FISICA/JURIDICA)")
    private TipoPessoaEnum tipoPessoa;

    @NotNull
    @Schema(title = "ID Profissão", example = "1", description = "ID da profissão do provedor de serviços.")
    private Long idProfissao;

    @Schema(title = "Imagens dos servicos")
    private List<String> servicoImagens;

    private PerfilProvedorInput perfilProvedorInput;
}
