package br.forsign.allo.provedor.converter;


import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorOutput;
import org.mapstruct.Mapper;

@Mapper
public interface ProvedorMapper {


    ProvedorOutput toOutput(Provedor provedor);
}
