package br.forsign.allo.entidade.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 16/06/2024
 */

@Data
public class EnderecoInput {

    @Schema(title = "Identificador", example = "0", description = "Idetificador único do endereço.")
    private Long id;

    @NotBlank
    @Schema(example = "37706524", description = "CEP")
    private String cep;

    @NotBlank
    @Schema(example = "SP", description = "Sigla do estado")
    private String estado;

    @NotBlank
    @Schema(example = "São Paulo", description = "Nome da cidade")
    private String cidade;

    @NotBlank
    @Schema(example = "Vila Mariana", description = "Nome do bairro")
    private String bairro;

    @NotBlank
    @Schema(example = "Rua Vergueiro", description = "Nome do logradouro")
    private String logradouro;

    @NotBlank
    @Schema(example = "123", description = "Número do logradouro")
    private String numero;
}
