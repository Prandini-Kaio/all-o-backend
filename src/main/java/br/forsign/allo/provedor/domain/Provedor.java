package br.forsign.allo.provedor.domain;

import br.forsign.allo.entidade.domain.Endereco;
import br.forsign.allo.entidade.domain.Entidade;
import br.forsign.allo.entidade.model.TipoPessoaEnum;
import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "PROFISSAO_PROVEDOR",
            joinColumns = @JoinColumn(name = "ID_PROVEDOR"),
            inverseJoinColumns = @JoinColumn(name = "ID_PROFISSAO"))
    private List<Profissao> profissoes;

    @OneToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    @Column(name = "RAZAO_SOCIAL")
    private String razaoSocial;

    @OneToOne
    @JoinColumn(name = "ENDERECO_ID")
    private Endereco endereco;

    @Enumerated
    private TipoPessoaEnum tipoPessoa;

    private boolean ativo;

}


