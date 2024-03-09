package br.forsign.allo.provider.model;


import br.forsign.allo.provider.model.operationhour.OperationHourInputDTO;
import br.forsign.allo.provider.model.profession.ProfessionInputDTO;
import br.forsign.allo.user.domain.TipoPessoa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProviderInputDTO {
    @Schema(example = "Douglas Mecanico")
    private Long id;

    private String name;

    private String description;

    private ProfessionInputDTO profession;

    @Enumerated
    private TipoPessoa tipoPessoa;

    private Long userID;

    private OperationHourInputDTO operationHour;


}