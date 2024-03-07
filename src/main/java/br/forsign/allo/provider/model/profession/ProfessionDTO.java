package br.forsign.allo.provider.model.profession;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionDTO {
    private Long id;
    private String name;
    private String description;
}
