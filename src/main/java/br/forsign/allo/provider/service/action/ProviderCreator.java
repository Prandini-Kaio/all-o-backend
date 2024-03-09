package br.forsign.allo.provider.service.action;

import br.forsign.allo.provider.converter.ProviderConverter;
import br.forsign.allo.provider.domain.OperationHour;
import br.forsign.allo.provider.domain.Profession;
import br.forsign.allo.provider.domain.Provider;
import br.forsign.allo.provider.domain.ProviderRating;
import br.forsign.allo.provider.model.ProviderInputDTO;
import br.forsign.allo.provider.repository.ProviderRepository;
import br.forsign.allo.user.domain.User;
import br.forsign.allo.user.service.actions.UserGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProviderCreator {

    @Autowired
    private ProviderRepository repository;

    @Autowired
    private UserGetter userGetter;

    public Provider create(ProviderInputDTO inputDTO){

        User user = userGetter.getById(inputDTO.getUserID());

        Profession profession = ProviderConverter.fromProfessionInput(inputDTO.getProfession());
        ProviderRating providerRating = new ProviderRating();

        Provider provider = Provider.builder()
                .user(user)
                .name(inputDTO.getName())
                .description(inputDTO.getDescription())
                .profession(profession)
                .tipoPessoa(inputDTO.getTipoPessoa())
                .providerRating(providerRating)
                .operationHour(new OperationHour())
                .build();

        return repository.save(provider);
    }
}
