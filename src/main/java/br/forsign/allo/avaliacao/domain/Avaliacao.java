package br.forsign.allo.avaliacao.domain;

/*
 * @author prandini
 * created 5/26/24
 */

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.provedor.domain.Provedor;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "AVALIACAO")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PROVEDOR_ID")
    private Provedor provedor;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;

    @Column(name = "NOTA")
    private double nota;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DESCRICAO")
    private String descricao;
}
