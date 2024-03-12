package br.forsign.allo.provider.converter;

import br.forsign.allo.provider.domain.Profile;
import br.forsign.allo.provider.domain.Provider;
import br.forsign.allo.provider.model.ProviderOutputDTO;
import br.forsign.allo.provider.model.profile.ProfileDTO;
import br.forsign.allo.provider.model.profile.ProfileInputDTO;

public class ProviderConverter {

    public static ProviderOutputDTO toProviderDTO(Provider provider){
        return ProviderOutputDTO.builder()
                .id(provider.getId())
                .name(provider.getName())
                .profile(ProfileConverter.toDTO(provider.getProfile()))
                .evaluation(EvaluationConverter.toDTO(provider.getEvaluation()))
                .tipoPessoa(provider.getTipoPessoa())
                .operationHour(OperationHourConverter.toDTO(provider.getOperationHour())).build();
    }
}

