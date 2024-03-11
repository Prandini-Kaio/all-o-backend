package br.forsign.allo.provider.model;


import br.forsign.allo.provider.model.operationhour.OperationHourDTO;
import br.forsign.allo.provider.model.profession.ProfessionDTO;
import br.forsign.allo.user.domain.TipoPessoa;
import br.forsign.allo.user.model.UserOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProviderOutputDTO {
    private Long id;

    private String name;

    private String description;

    private ProfessionDTO profession;

    private TipoPessoa tipoPessoa;

    private UserOutputDTO user;

    private OperationHourDTO operationHour;

}