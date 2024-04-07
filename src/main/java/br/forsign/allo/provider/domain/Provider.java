package br.forsign.allo.provider.domain;

import br.forsign.allo.user.domain.TipoPessoa;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PROVEDOR")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@SequenceGenerator(name = "SEQ_PROVIDER")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @OneToOne(cascade = CascadeType.ALL)
    private Evaluation evaluation;

    @Enumerated
    @Column(name = "TIPO_PESSOA")
    private TipoPessoa tipoPessoa;

    @OneToOne(cascade = CascadeType.ALL)
    private OperationHour operationHour;

}


