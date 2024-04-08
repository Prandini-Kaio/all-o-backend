package br.forsign.allo.provider.model.rating;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RatingInputDTO {
    @Schema(example = "3.5")
    private float total;

    @Schema(example = "3.5")
    private float media;
}
