package br.forsign.allo.provider.converter;

import br.forsign.allo.provider.domain.OperationHour;
import br.forsign.allo.provider.domain.Profession;
import br.forsign.allo.provider.domain.Provider;
import br.forsign.allo.provider.domain.ProviderRating;
import br.forsign.allo.provider.model.ProviderOutputDTO;
import br.forsign.allo.provider.model.operationhour.OperationHourDTO;
import br.forsign.allo.provider.model.profession.ProfessionDTO;
import br.forsign.allo.provider.model.profession.ProfessionInputDTO;
import br.forsign.allo.provider.model.providerrating.ProviderRatingInputDTO;
import br.forsign.allo.user.converter.UserConverter;

public class ProviderConverter {

    public static ProviderOutputDTO toOutput(Provider provider){
        return ProviderOutputDTO.builder()
                .id(provider.getId())
                .name(provider.getName())
                .user(UserConverter.toUserDTO(provider.getUser()))
                .description(provider.getDescription())
                .profession(toProfessionDTO(provider.getProfession()))
                .tipoPessoa(provider.getTipoPessoa())
                .operationHour(provider.getOperationHour().toOutput())
                .build();
    }

    public static Profession fromProfessionInput(ProfessionInputDTO inputDTO){
        return Profession.builder()
                .name(inputDTO.getName())
                .description(inputDTO.getDescription())
                .build();
    }

    public static ProfessionDTO toProfessionDTO(Profession profession){
        return new ProfessionDTO(profession.getId(), profession.getName(), profession.getDescription());
    }

}
