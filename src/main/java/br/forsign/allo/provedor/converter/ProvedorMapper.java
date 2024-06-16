package br.forsign.allo.provedor.converter;


import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProvedorMapper {

    @Mapping(source = "profissao.nome", target = "profissaoOutput")
    ProvedorOutput toOutput(Provedor provedor);
}
