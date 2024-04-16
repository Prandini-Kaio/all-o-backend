package br.forsign.allo.provider.model;


import br.forsign.allo.provider.model.rating.RatingInputDTO;
import br.forsign.allo.provider.model.operationhour.OperationHourInputDTO;
import br.forsign.allo.provider.model.profile.ProfileInputDTO;
import br.forsign.allo.user.domain.TipoPessoa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProviderInputDTO {
    @Schema(example = "Douglas Mecanico")
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private ProfileInputDTO profile;

    private RatingInputDTO rating;

    @NotNull
    private TipoPessoa tipoPessoa;

    private OperationHourInputDTO operationHour;


}