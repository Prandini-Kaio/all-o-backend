package br.forsign.allo.provedor.converter;

import br.forsign.allo.provedor.domain.PerfilProvedor;
import br.forsign.allo.provedor.model.PerfilProvedorInput;
import br.forsign.allo.provedor.model.PerfilProvedorOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PerfilProvedorMapper {

    PerfilProvedorOutput toOutput(PerfilProvedor perfilProvedor);

    @Mapping(source = "perfilProvedor.id", target = "idAvaliacao")
    PerfilProvedorInput toInput(PerfilProvedor perfilProvedor);

}
