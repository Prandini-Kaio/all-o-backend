package br.forsign.allo.profissao.converter;


import br.forsign.allo.profissao.domain.Profissao;
import br.forsign.allo.profissao.model.ProfissaoOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ProfissaoMapper {

    ProfissaoOutput toOutput(Profissao profissao);

    List<Profissao> toOutputList(List<Profissao> profissao);
}
