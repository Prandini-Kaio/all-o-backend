package br.forsign.allo.provider.converter;

import br.forsign.allo.provider.domain.Establishment;
import br.forsign.allo.provider.model.establishment.EstablishmentDTO;

public class EstablishmentConverter {
    public static EstablishmentDTO toDTO(Establishment establishment){
        return new EstablishmentDTO(establishment.getId(), establishment.getName());
    }
}
