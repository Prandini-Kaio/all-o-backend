package br.forsign.allo.cliente.domain;

import br.forsign.allo.entidade.domain.Entidade;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "CLIENTE")
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Entidade {

    @Column(name = "NOME")
    private String nome;

    @Column(name = "ATIVO")
    private boolean ativo;
}
