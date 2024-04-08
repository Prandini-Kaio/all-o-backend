package br.forsign.allo.provider.service.action;

import br.forsign.allo.provider.domain.Provider;
import br.forsign.allo.provider.model.ProviderInputDTO;
import br.forsign.allo.provider.repository.ProviderRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ProviderUpdater {

    @Resource
    private ProviderRepository repository;

    public Provider update(ProviderInputDTO inputDTO){
        Provider provider = new Provider();

        provider = repository.findById(inputDTO.getId()).orElse(null);

        provider.setName(inputDTO.getName());
        provider.setTipoPessoa(inputDTO.getTipoPessoa());
        //Evaluation
        provider.getRating().setTotal(inputDTO.getRating().getTotal());
        //Profile
        provider.getProfile().setExperience(inputDTO.getProfile().getExperience());
        provider.getProfile().setSpecification(inputDTO.getProfile().getSpecification());
        provider.getProfile().setCertification(inputDTO.getProfile().getCertification());
        provider.getProfile().setDescription(inputDTO.getProfile().getDescription());
        //OperationHour
        provider.getOperationHour().setOpenHour(inputDTO.getOperationHour().getOpenHour());
        provider.getOperationHour().setBreakTime(inputDTO.getOperationHour().getBreakTime());
        provider.getOperationHour().setBreakReturn(inputDTO.getOperationHour().getBreakReturn());
        provider.getOperationHour().setCloseHour(inputDTO.getOperationHour().getCloseHour());

        repository.save(provider);

    return provider;
    }

}
