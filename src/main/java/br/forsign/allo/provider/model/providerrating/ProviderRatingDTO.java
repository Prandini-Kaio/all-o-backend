package br.forsign.allo.provider.model.providerrating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderRatingDTO {
    private Long id;
    private float total;
    private float media;
}
