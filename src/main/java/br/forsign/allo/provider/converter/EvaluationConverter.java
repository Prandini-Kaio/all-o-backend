package br.forsign.allo.provider.converter;

import br.forsign.allo.provider.domain.Evaluation;
import br.forsign.allo.provider.model.evaluation.EvaluationDTO;

public class EvaluationConverter {
    public static EvaluationDTO toDTO(Evaluation evaluation){
        return new EvaluationDTO(evaluation.getId(), evaluation.getTotal(), evaluation.getMedia());
    }
}
