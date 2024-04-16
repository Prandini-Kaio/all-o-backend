package br.forsign.allo.provider.model.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfileInputDTO {

    @Schema(example = "Faço consertos mecânicos em automoveis.")
    private String description;

    private String certification;

    private String specification;

    private String experience;
}
