package br.forsign.allo.provider.converter;

import br.forsign.allo.provider.domain.Profile;
import br.forsign.allo.provider.model.profile.ProfileDTO;

public class ProfileConverter {

    public static ProfileDTO toDTO(Profile profile){
        return new ProfileDTO(profile.getId(),
                profile.getDescription(),
                profile.getCertification(),
                profile.getSpecification(),
                profile.getExperience());
    }
}
