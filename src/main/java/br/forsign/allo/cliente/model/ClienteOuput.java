package br.forsign.allo.cliente.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteOuput {

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private String telefone;

    private boolean ativo;

}
