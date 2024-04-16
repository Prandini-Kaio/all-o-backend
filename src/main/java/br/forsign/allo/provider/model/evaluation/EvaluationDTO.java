package br.forsign.allo.provider.model.evaluation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationDTO {
    private Long id;
    private float total;
    private float media;
}
