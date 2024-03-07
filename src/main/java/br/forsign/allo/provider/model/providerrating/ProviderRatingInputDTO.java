package br.forsign.allo.provider.model.providerrating;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProviderRatingInputDTO {
    @Schema(example = "3.5")
    private float total;

    @Schema(example = "3.5")
    private float media;
}
