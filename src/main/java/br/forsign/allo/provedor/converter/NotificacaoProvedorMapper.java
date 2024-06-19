package br.forsign.allo.provedor.converter;

import br.forsign.allo.provedor.domain.NotificacaoProvedor;
import br.forsign.allo.provedor.model.NotificacaoOutput;
import org.mapstruct.Mapper;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Mapper
public interface NotificacaoProvedorMapper {

    NotificacaoOutput toOutput(NotificacaoProvedor notificacao);
}
