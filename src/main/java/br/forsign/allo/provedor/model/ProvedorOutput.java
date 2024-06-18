package br.forsign.allo.provedor.model;


import br.forsign.allo.entidade.model.EnderecoOutput;
import br.forsign.allo.entidade.model.TipoPessoaEnum;
import br.forsign.allo.profissao.model.ProfissaoOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProvedorOutput {

    private Long id;

    private String razaoSocial;

    private EnderecoOutput endereco;

    private List<ProfissaoOutput> profissoes;

    private TipoPessoaEnum tipoPessoa;

    private String cpfCnpj;

    private String email;

    private String telefone;

    private boolean ativo;

    private boolean favorito;

}
