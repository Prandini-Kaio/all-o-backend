package br.forsign.allo.cliente.domain;

import br.forsign.allo.entidade.domain.Entidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "CLIENTE")
@Data
@EqualsAndHashCode(callSuper = true)
@SequenceGenerator(name = "SEQ_CLIENTE")
public class Cliente extends Entidade {

    @Column(name = "NOME")
    private String nome;

    @Column(name = "ATIVO")
    private boolean ativo;
}
