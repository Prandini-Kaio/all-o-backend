package br.forsign.allo.user.model.document;

import br.forsign.allo.common.utils.annotation.CpfCnpj;
import br.forsign.allo.user.domain.TipoPessoa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocumentInputDTO {
    @Schema(example = "27482923080")
    @CpfCnpj(tipoDocumento = TipoPessoa.FISICA)
    private String cpfCnpj;
}
