package br.forsign.allo.entidade.converter;

import br.forsign.allo.entidade.domain.Endereco;
import br.forsign.allo.entidade.model.EnderecoInput;
import br.forsign.allo.entidade.model.EnderecoOutput;
import org.mapstruct.Mapper;

/**
 * @author kaiooliveira
 * created 16/06/2024
 */

@Mapper
public interface EnderecoMapper {

    EnderecoOutput toOutput(Endereco endereco);

    Endereco fromInput(EnderecoInput enderecoInput);
}
