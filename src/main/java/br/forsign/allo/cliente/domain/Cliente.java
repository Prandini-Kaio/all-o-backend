package br.forsign.allo.cliente.domain;

import br.forsign.allo.entidade.domain.Entidade;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "CLIENTE")
@EqualsAndHashCode(callSuper = true)
@SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "SEQ_CLIENTE")
public class Cliente extends Entidade {

    @Id
    @GeneratedValue(generator = "SEQ_CLIENTE")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "ATIVO")
    private boolean ativo;
}
