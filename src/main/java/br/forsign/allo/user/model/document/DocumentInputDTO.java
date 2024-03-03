package br.forsign.allo.user.model.document;


import br.forsign.allo.common.utils.annotation.CpfCnpj;
import br.forsign.allo.user.domain.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocumentInputDTO {
    @CpfCnpj(tipoDocumento = TipoDocumento.CPF)
    private String cpfCnpj;
}
