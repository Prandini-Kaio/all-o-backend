package br.forsign.allo.provider.model;


import br.forsign.allo.provider.model.rating.RatingDTO;
import br.forsign.allo.provider.model.operationhour.OperationHourDTO;
import br.forsign.allo.provider.model.profile.ProfileDTO;
import br.forsign.allo.user.domain.TipoPessoa;
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

    private ProfileDTO profile;

    private RatingDTO rating;

    private TipoPessoa tipoPessoa;

    private OperationHourDTO operationHour;

}