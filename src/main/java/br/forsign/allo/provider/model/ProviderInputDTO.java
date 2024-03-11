package br.forsign.allo.provider.model;


import br.forsign.allo.provider.model.operationhour.OperationHourInputDTO;
import br.forsign.allo.provider.model.profession.ProfessionInputDTO;
import br.forsign.allo.user.domain.TipoPessoa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Enumerated;
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

    private String description;

    @NotNull
    private ProfessionInputDTO profession;

    @NotNull
    private TipoPessoa tipoPessoa;

    @NotNull
    private Long userID;

    private OperationHourInputDTO operationHour;


}