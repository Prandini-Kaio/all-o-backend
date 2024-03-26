package br.forsign.allo.provider.converter;

import br.forsign.allo.provider.domain.Rating;
import br.forsign.allo.provider.domain.OperationHour;
import br.forsign.allo.provider.domain.Profile;
import br.forsign.allo.provider.domain.Provider;
import br.forsign.allo.provider.model.ProviderOutputDTO;
import br.forsign.allo.provider.model.rating.RatingDTO;
import br.forsign.allo.provider.model.rating.RatingInputDTO;
import br.forsign.allo.provider.model.operationhour.OperationHourDTO;
import br.forsign.allo.provider.model.profile.ProfileDTO;
import br.forsign.allo.provider.model.profile.ProfileInputDTO;

public class ProviderConverter {

    public static ProviderOutputDTO toProviderDTO(Provider provider){
        return ProviderOutputDTO.builder()
                .id(provider.getId())
                .name(provider.getName())
                .profile(toProfileToOutput(provider.getProfile()))
                .rating(toRatingToOutput(provider.getRating()))
                .tipoPessoa(provider.getTipoPessoa())
                .operationHour(toOpHourOutput(provider.getOperationHour())).build();
    }

    public static ProfileDTO toProfileToOutput(Profile profile){
        return new ProfileDTO(
                profile.getId(),
                profile.getCertification(),
                profile.getDescription(),
                profile.getSpecification(),
                profile.getExperience());
    }

    public static RatingDTO toRatingToOutput(Rating evaluation){
        return new RatingDTO(
                evaluation.getId(),
                evaluation.getTotal(),
                evaluation.getMedia());
    }

    public static OperationHourDTO toOpHourOutput(OperationHour operationHour){
        return new OperationHourDTO(
                operationHour.getId(),
                operationHour.getOpenHour(),
                operationHour.getBreakTime(),
                operationHour.getBreakReturn(),
                operationHour.getCloseHour());
    }

    public static Profile getProfile(ProfileInputDTO inputDTO){
        return Profile.builder()
                .description(inputDTO.getDescription())
                .certification(inputDTO.getCertification())
                .specification(inputDTO.getSpecification())
                .experience(inputDTO.getExperience())
                .build();
    }

    public static Rating getRating(RatingInputDTO inputDTO){
        return Rating.builder()
                .total(inputDTO.getTotal())
                .media(inputDTO.getMedia())
                .build();
    }
}

