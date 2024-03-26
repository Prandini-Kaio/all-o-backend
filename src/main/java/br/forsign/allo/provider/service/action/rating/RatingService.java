package br.forsign.allo.provider.service.action.rating;

import br.forsign.allo.provider.converter.RatingConverter;
import br.forsign.allo.provider.model.rating.RatingDTO;
import br.forsign.allo.provider.model.rating.RatingInputDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Resource
    private RatingCreator creator;

    public RatingDTO create(RatingInputDTO inputDTO){
        return RatingConverter.toDTO(creator.create(inputDTO));
    }
}
