package br.forsign.allo.entidade.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/*
 * @author prandini
 * created 5/22/24
 */

@Data
public class EntidadeInput {

    @Schema(title = "Identificador", example = "3")
    private Long id;

    @Schema(title = "Email", example = "example@org.com")
    private String email;

    @Schema(title = "Telefone", example = "+55 33 978890000")
    private String telefone;

    @Schema(title = "CPF CNPJ", example = "000.000.000-00")
    private String cpfCnpj;
}
