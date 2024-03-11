package br.forsign.allo.provider.converter;

import br.forsign.allo.provider.domain.Profession;
import br.forsign.allo.provider.model.profession.ProfessionDTO;

public class ProfessionConverter {
    public static ProfessionDTO toDTO(Profession profession){
        return new ProfessionDTO(profession.getId(), profession.getName(), profession.getDescription());
    }
}