package br.forsign.allo.provider.converter;

import br.forsign.allo.provider.domain.Evaluation;
import br.forsign.allo.provider.domain.OperationHour;
import br.forsign.allo.provider.domain.Profile;
import br.forsign.allo.provider.domain.Provider;
import br.forsign.allo.provider.model.ProviderOutputDTO;
import br.forsign.allo.provider.model.evaluation.EvaluationDTO;
import br.forsign.allo.provider.model.evaluation.EvaluationInputDTO;
import br.forsign.allo.provider.model.operationhour.OperationHourDTO;
import br.forsign.allo.provider.model.profile.ProfileDTO;
import br.forsign.allo.provider.model.profile.ProfileInputDTO;

public class ProviderConverter {

    public static ProviderOutputDTO toProviderDTO(Provider provider){
        return ProviderOutputDTO.builder()
                .id(provider.getId())
                .name(provider.getName())
                .profile(toProfileToOutput(provider.getProfile()))
                .evaluation(toEvaluationToOutput(provider.getEvaluation()))
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

    public static EvaluationDTO toEvaluationToOutput(Evaluation evaluation){
        return new EvaluationDTO(
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

    public static Evaluation getEvaluation(EvaluationInputDTO inputDTO){
        return Evaluation.builder()
                .total(inputDTO.getTotal())
                .media(inputDTO.getMedia())
                .build();
    }
}

