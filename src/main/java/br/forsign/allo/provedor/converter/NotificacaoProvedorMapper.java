package br.forsign.allo.provedor.converter;

import br.forsign.allo.provedor.domain.NotificacaoProvedor;
import br.forsign.allo.provedor.model.NotificacaoOutput;
import br.forsign.allo.provedor.model.NotificacaoProvedorOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Mapper
public interface NotificacaoProvedorMapper {

    NotificacaoOutput toOutput(NotificacaoProvedor notificacao);

    @Mapping(target = "nomeCliente", source = "nomeCliente")
    @Mapping(target = "mensagem", source = "mensagem")
    @Mapping(target = "dtRegistro", source = "dataCriacao")
    NotificacaoProvedorOutput toProvedorOutput(NotificacaoProvedor notificacao);
}
