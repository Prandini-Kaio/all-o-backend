package br.forsign.allo.provedor.converter;


import br.forsign.allo.entidade.domain.Endereco;
import br.forsign.allo.entidade.model.EnderecoOutput;
import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.model.ProvedorOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ProvedorMapper {

    @Mapping(source = "endereco", target = "endereco")
    ProvedorOutput toOutput(Provedor provedor);

    Provedor fromOutput(ProvedorOutput input);
}
