package br.forsign.allo.provedor.converter;

import br.forsign.allo.provedor.domain.PerfilProvedor;
import br.forsign.allo.provedor.model.PerfilProvedorOutput;
import org.mapstruct.Mapper;

@Mapper
public interface PerfilProvedorMapper {

    PerfilProvedorOutput toOutput(PerfilProvedor perfilProvedor);
}
