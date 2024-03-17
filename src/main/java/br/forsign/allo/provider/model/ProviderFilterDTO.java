package br.forsign.allo.provider.model;

import br.forsign.allo.provider.model.operationhour.OperationHourInputDTO;
import br.forsign.allo.provider.model.profession.ProfessionInputDTO;
import br.forsign.allo.user.domain.TipoPessoa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * Created By Kaio Prandini
 * Date: 3/16/24
 * Time: 3:22 PM
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProviderFilterDTO {
    private Long id;

    private String name;

    private String description;

    private String profession;

    private TipoPessoa tipoPessoa;

}
