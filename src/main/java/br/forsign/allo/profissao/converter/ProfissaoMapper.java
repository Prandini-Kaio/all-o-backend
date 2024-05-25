package br.forsign.allo.profissao.converter;


import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.profissao.model.ProfissaoOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProfissaoMapper {

    @Mapping(target = "categoria", source = "categoria")
    ProfissaoOutput toOutput(Profissao profissao);
}
