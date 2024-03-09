package br.forsign.allo.provider.domain;

import br.forsign.allo.user.domain.TipoPessoa;
import br.forsign.allo.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PROVEDOR")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String name;

    @Column(name = "DESCRICAO")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private Profession profession;

    @OneToOne(cascade = CascadeType.PERSIST)
    private ProviderRating providerRating;

    @Column(name = "TIPO_PESSOA")
    private TipoPessoa tipoPessoa;

    @OneToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private OperationHour operationHour;

}