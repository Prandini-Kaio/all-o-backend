package br.forsign.allo.provedor.domain;

import br.forsign.allo.entidade.domain.Entidade;
import br.forsign.allo.entidade.model.TipoPessoaEnum;
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
@SequenceGenerator(name = "SEQ_PROVEDOR")
public class Provedor extends Entidade {

    @Column(name = "RAZAO_SOCIAL")
    private String razaoSocial;

    @Enumerated
    private TipoPessoaEnum tipoPessoa;

    private boolean ativo;

}


