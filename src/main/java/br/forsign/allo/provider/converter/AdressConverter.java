package br.forsign.allo.provider.converter;

import br.forsign.allo.provider.domain.Adress;
import br.forsign.allo.provider.model.adress.AdressDTO;

public class AdressConverter {
    public static AdressDTO toDTO(Adress adress){
        return new AdressDTO(adress.getId(), adress.getName());
    }
}
