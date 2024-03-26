package br.forsign.allo.provider.converter;

import br.forsign.allo.provider.domain.Rating;
import br.forsign.allo.provider.model.rating.RatingDTO;

public class RatingConverter {
    public static RatingDTO toDTO(Rating rating){
        return new RatingDTO(rating.getId(), rating.getTotal(), rating.getMedia());
    }
}
