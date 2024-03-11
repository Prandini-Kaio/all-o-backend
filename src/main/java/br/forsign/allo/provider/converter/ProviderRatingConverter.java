package br.forsign.allo.provider.converter;

import br.forsign.allo.provider.domain.ProviderRating;
import br.forsign.allo.provider.model.providerrating.ProviderRatingDTO;

public class ProviderRatingConverter {
    public static ProviderRatingDTO toDTO(ProviderRating providerRating){
        return new ProviderRatingDTO(providerRating.getId(), providerRating.getTotal(), providerRating.getMedia());
    }
}
