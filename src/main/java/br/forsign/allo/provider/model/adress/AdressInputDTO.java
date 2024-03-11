package br.forsign.allo.provider.model.adress;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdressInputDTO {
    @Schema(example = "Mecanica do Douglao")
    private String name;
}
