package br.forsign.allo.provedor.converter;

import br.forsign.allo.provedor.domain.PerfilProvedor;
import br.forsign.allo.provedor.model.PerfilProvedorInput;
import br.forsign.allo.provedor.model.PerfilProvedorOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PerfilProvedorMapper {

    @Mapping(source = "totalAvaliacao", target = "totalAvaliacoes")
    PerfilProvedorOutput toOutput(PerfilProvedor perfilProvedor);

    PerfilProvedorInput toInput(PerfilProvedor perfilProvedor);

}
