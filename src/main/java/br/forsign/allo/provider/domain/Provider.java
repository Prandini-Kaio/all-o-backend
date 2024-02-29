package br.forsign.allo.provider.domain;

import br.forsign.allo.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PROVEDOR")
@Data
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Service service;

    @OneToOne(cascade = CascadeType.ALL)
    private Establishment establishment;

    @OneToOne(cascade = CascadeType.PERSIST)
    private ProviderRating providerRating;

    @Column(name = "TIPO_PESSOA")
    private String tipoPessoa;

    @OneToOne
    private User usuario;

    @OneToOne(cascade = CascadeType.ALL)
    private RegisterDateTime registerDateTime;

    @OneToOne(cascade = CascadeType.ALL)
    private OperationHour operationHour;

}
