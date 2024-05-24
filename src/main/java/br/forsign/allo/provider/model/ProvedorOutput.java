package br.forsign.allo.provider.model;


import br.forsign.allo.user.domain.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProvedorOutput {

    private Long id;

    private String razaoSocial;

    private TipoPessoa tipoPessoa;

    private String cpfCnpj;

    private String email;

    private String telefone;

    private boolean ativo;

}