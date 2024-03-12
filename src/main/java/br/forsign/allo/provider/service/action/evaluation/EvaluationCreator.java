package br.forsign.allo.provider.service.action.evaluation;

import br.forsign.allo.provider.domain.Evaluation;
import br.forsign.allo.provider.model.evaluation.EvaluationInputDTO;
import br.forsign.allo.provider.repository.EvaluationRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class EvaluationCreator {

    @Resource
    private EvaluationRepository repository;

    public Evaluation create(EvaluationInputDTO input){
        Evaluation evaluation = new Evaluation();
        evaluation.setMedia(input.getMedia());
        evaluation.setTotal(input.getTotal());

        return repository.save(evaluation);
    }
}
