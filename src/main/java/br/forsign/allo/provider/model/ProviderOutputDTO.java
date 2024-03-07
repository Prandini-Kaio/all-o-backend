package br.forsign.allo.provider.model;


import br.forsign.allo.provider.model.establishment.EstablishmentDTO;
import br.forsign.allo.provider.model.operationhour.OperationHourDTO;
import br.forsign.allo.provider.model.profession.ProfessionDTO;
import br.forsign.allo.provider.model.providerrating.ProviderRatingDTO;
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
    private ProfessionDTO profession;
    private EstablishmentDTO establishment;
    private ProviderRatingDTO providerrating;
    private String tipoPessoa;
    private UserOutputDTO user;
    private OperationHourDTO operationhour;

}
