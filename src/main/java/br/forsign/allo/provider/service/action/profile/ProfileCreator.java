package br.forsign.allo.provider.service.action.profile;

import br.forsign.allo.provider.domain.Profile;
import br.forsign.allo.provider.model.profile.ProfileInputDTO;
import br.forsign.allo.provider.repository.ProfileRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProfileCreator {

    @Resource
    private ProfileRepository repository;

    public Profile create(ProfileInputDTO input){
        Profile profile = new Profile();
        profile.setCertification(input.getCertification());
        profile.setSpecification(input.getSpecification());
        profile.setDescription(input.getDescription());
        profile.setExperience(input.getExperience());

        return repository.save(profile);
    }
}
