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

    @Schema(title = "Identificador", example = "3", description = "Idetificador único da entidade.")
    private Long id;

    @Schema(title = "Email", example = "example@org.com", description = "E-mail de referência da entidade.")
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @Schema(title = "Telefone", example = "+55 33 978890000", description = "Telefone principal da entidade.")
    @Pattern(regexp = "^(\\d{2,3})(\\d{9})$")
    private String telefone;

    @Schema(title = "CPF CNPJ", example = "000.000.000-00", description = "CPF ou CNPJ da entidade.")
    @CpfCnpj(tipoDocumento = TipoPessoaEnum.FISICA_JURIDICA)
    private String cpfCnpj;
}
