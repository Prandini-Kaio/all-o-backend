package br.forsign.allo.provedor.domain;

import br.forsign.allo.entidade.domain.Notificacao;
import jakarta.persistence.JoinColumn;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */
public class NotificacaoProvedor extends Notificacao {

    @JoinColumn(name = "PROVEDOR_ID")
    private Provedor provedor;
}
