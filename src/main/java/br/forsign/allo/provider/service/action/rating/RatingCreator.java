package br.forsign.allo.provider.service.action.rating;

import br.forsign.allo.provider.domain.Rating;
import br.forsign.allo.provider.model.rating.RatingInputDTO;
import br.forsign.allo.provider.repository.RatingRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RatingCreator {

    @Resource
    private RatingRepository repository;

    public Rating create(RatingInputDTO input){
        Rating evaluation = new Rating();
        evaluation.setMedia(input.getMedia());
        evaluation.setTotal(input.getTotal());

        return repository.save(evaluation);
    }
}
