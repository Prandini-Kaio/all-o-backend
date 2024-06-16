package br.forsign.allo.provedor.domain;

import br.forsign.allo.entidade.domain.Entidade;
import br.forsign.allo.entidade.model.TipoPessoaEnum;
import br.forsign.allo.profissao.domain.Profissao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROVEDOR")
@SequenceGenerator(name = "SEQ_PRVDR", sequenceName = "SEQ_PRVDR")
public class Provedor extends Entidade {

    @Id
    @GeneratedValue(generator = "SEQ_PRVDR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFISSAO_ID")
    private Profissao profissao;

    @Column(name = "RAZAO_SOCIAL")
    private String razaoSocial;

    @Enumerated
    private TipoPessoaEnum tipoPessoa;

    private boolean ativo;

}


