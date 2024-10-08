package br.forsign.allo.cliente.domain;

import br.forsign.allo.entidade.domain.Endereco;
import br.forsign.allo.entidade.domain.Entidade;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@Entity
@Table(name = "CLIENTE")
@SequenceGenerator(name = "SEQ_CLNT", sequenceName = "SEQ_CLNT")
public class Cliente extends Entidade {

    @Id
    @GeneratedValue(generator = "SEQ_CLNT")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "PERFIL",
            referencedColumnName = "ID"
    )
    private PerfilCliente perfil;

    @OneToOne
    @JoinColumn(
            name = "USUARIO_ID",
            referencedColumnName = "ID"
    )
    private Usuario usuario;

    @OneToMany
    @JoinTable(
            name = "CLIENTE_PROVEDOR_FAVORITO",
            joinColumns = @JoinColumn(name = "CLIENTE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROVEDOR_ID")
    )
    private Set<Provedor> provedoresFavoritados;

    @OneToOne
    @JoinColumn(name = "ENDERECO_ID")
    private Endereco endereco;

    @Column(name = "ATIVO")
    private boolean ativo;
}
