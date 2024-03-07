package br.forsign.allo.provider.model.profession;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfessionInputDTO {
    @Schema(example = "Mecanico")
    private String name;
    @Schema(example = "Faço consertos mecânicos em automoveis.")
    private String description;
}
