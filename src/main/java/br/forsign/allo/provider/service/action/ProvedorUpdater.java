package br.forsign.allo.provider.service.action;

import br.forsign.allo.provider.domain.Provedor;
import br.forsign.allo.provider.model.ProvedorInput;
import br.forsign.allo.provider.repository.ProvedorRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ProviderUpdater {

    @Resource
    private ProvedorRepository repository;

    public Provedor update(ProvedorInput inputDTO){
        Provedor provedor = new Provedor();

        provedor = repository.findById(inputDTO.getId()).orElse(null);

        provedor.setName(inputDTO.getRazaoSocial());
        provedor.setTipoPessoa(inputDTO.getTipoPessoa());
        //Evaluation
        provedor.getEvaluation().setTotal(inputDTO.getEvaluation().getTotal());
        //Profile
        provedor.getProfile().setExperience(inputDTO.getProfile().getExperience());
        provedor.getProfile().setSpecification(inputDTO.getProfile().getSpecification());
        provedor.getProfile().setCertification(inputDTO.getProfile().getCertification());
        provedor.getProfile().setDescription(inputDTO.getProfile().getDescription());
        //OperationHour
        provedor.getOperationHour().setHoraAbertura(inputDTO.getOperationHour().getOpenHour());
        provedor.getOperationHour().setHoraPausa(inputDTO.getOperationHour().getBreakTime());
        provedor.getOperationHour().setHoraRetorno(inputDTO.getOperationHour().getBreakReturn());
        provedor.getOperationHour().setHoraFechamento(inputDTO.getOperationHour().getCloseHour());

        repository.save(provedor);

    return provedor;
    }

}
