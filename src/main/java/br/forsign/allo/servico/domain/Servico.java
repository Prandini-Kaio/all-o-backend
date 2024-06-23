package br.forsign.allo.servico.domain;

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.provedor.domain.Provedor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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

    @OneToOne
    private Provedor provedor;

    @OneToOne
    private Cliente cliente;

    @OneToOne
    private Avaliacao avaliacao;

    private boolean servicoRealizado;
}
