package br.forsign.allo.servico.converter;

import br.forsign.allo.servico.domain.Servico;
import br.forsign.allo.servico.model.ServicoOutput;
import org.mapstruct.Mapper;

/**
 * @author kaiooliveira
 * created 23/06/2024
 */

@Mapper
public interface ServicoMapper {

    ServicoOutput toOutput(Servico servico);
}
