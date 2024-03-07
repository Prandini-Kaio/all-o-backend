package br.forsign.allo.provider.model.establishment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentInputDTO {
    @Schema(example = "Mecanica do Douglao")
    private String name;
}
