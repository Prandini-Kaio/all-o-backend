package br.forsign.allo.provider.model;

import br.forsign.allo.provider.model.establishment.EstablishmentInputDTO;
import br.forsign.allo.provider.model.operationhour.OperationHourInputDTO;
import br.forsign.allo.provider.model.profession.ProfessionInputDTO;
import br.forsign.allo.provider.model.providerrating.ProviderRatingInputDTO;
import br.forsign.allo.user.model.UserInputDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProviderInputDTO {
    @NotBlank
    @Schema(example = "Douglas Mecanico")
    private Long id;
    private ProfessionInputDTO profession;
    private EstablishmentInputDTO establishment;
    private ProviderRatingInputDTO providerrating;
    @Schema(example = "Pessoa Fisica")
    private String tipoPessoa;
    private UserInputDTO user;
    private OperationHourInputDTO operationhour;


}
