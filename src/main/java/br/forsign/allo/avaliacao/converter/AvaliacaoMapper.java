package br.forsign.allo.avaliacao.converter;

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.avaliacao.model.AvaliacaoOutput;
import org.mapstruct.Mapper;

@Mapper
public interface AvaliacaoMapper {

    AvaliacaoOutput toOutput(Avaliacao avaliacao);

    Avaliacao fromOutput(AvaliacaoOutput avaliacaoOutput);
}
