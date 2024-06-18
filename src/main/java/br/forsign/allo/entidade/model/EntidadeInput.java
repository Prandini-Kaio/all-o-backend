package br.forsign.allo.entidade.model;

import br.forsign.allo.common.utils.annotation.CpfCnpj;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/*
 * @author prandini
 * created 5/22/24
 */

@Data
public class EntidadeInput {

    @Schema(title = "Identificador", example = "0", description = "Idetificador único da entidade.")
    private Long id;

    @Schema(title = "Email", example = "example@org.com", description = "E-mail de referência da entidade.")
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @Schema(title = "Telefone", example = "33978890000", description = "Telefone principal da entidade.")
    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}-[0-9]{4}$")
    private String telefone;

    @Schema(title = "CPF CNPJ", example = "869.039.770-15", description = "CPF ou CNPJ da entidade.")
    @CpfCnpj(tipoDocumento = TipoPessoaEnum.FISICA_JURIDICA)
    private String cpfCnpj;
}
