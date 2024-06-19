package br.forsign.allo.provedor.domain;

import br.forsign.allo.entidade.domain.Notificacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Data
@Entity
@Table(name = "NOTIFICACAO_PROVEDOR")
public class NotificacaoProvedor extends Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "PROVEDOR_ID")
    private Provedor provedor;
}
