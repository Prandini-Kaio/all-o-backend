package br.forsign.allo.provider.model.adress;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdressInputDTO {
    private String cep;
    @Schema(example = "Rua dos Lobos")
    private String streetname;
    @Schema(example = "69")
    private int number;
    @Schema(example = "Apto. 8")
    private String complement;
}
