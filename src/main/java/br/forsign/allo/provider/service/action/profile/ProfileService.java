package br.forsign.allo.provider.service.action.profile;

import br.forsign.allo.provider.converter.ProfileConverter;
import br.forsign.allo.provider.model.profile.ProfileDTO;
import br.forsign.allo.provider.model.profile.ProfileInputDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Resource
    private ProfileCreator creator;

    public ProfileDTO create(ProfileInputDTO inputDTO){
        return ProfileConverter.toDTO(creator.create(inputDTO));
    }
}
