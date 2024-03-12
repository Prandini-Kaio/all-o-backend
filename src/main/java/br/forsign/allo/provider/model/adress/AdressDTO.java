package br.forsign.allo.provider.model.adress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdressDTO {
    private Long id;
    private String cep;
    private String streetname;
    private int number;
    private String complement;
}
