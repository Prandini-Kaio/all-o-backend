package br.forsign.allo.cliente.model;

import br.forsign.allo.provedor.model.ProvedorOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

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

    private Set<ProvedorOutput> favoritos;

    private boolean ativo;

}
