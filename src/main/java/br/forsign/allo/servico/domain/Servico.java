package br.forsign.allo.servico.domain;

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.provedor.domain.Provedor;
import jakarta.persistence.*;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 22/06/2024
 */

@Data
@Entity
@Table(name = "SERVICO")
@SequenceGenerator(name = "SEQ_SERV", sequenceName = "SEQ_SERV")
public class Servico {

    @Id
    @GeneratedValue(generator = "SEQ_SERV")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PROVEDOR")
    private Provedor provedor;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL)
    private Avaliacao avaliacao;

    private boolean servicoRealizado;

    private boolean servicoVisto;
}
