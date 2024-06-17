package br.forsign.allo.entidade.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 16/06/2024
 */

@Data
public class EnderecoOutput {
    private Long id;

    private String cep;

    private String estado;

    private String cidade;

    private String bairro;

    private String logradouro;

    private String numero;
}
