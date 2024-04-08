package br.forsign.allo.provider.service.action.adress;

import br.forsign.allo.provider.converter.AdressConverter;
import br.forsign.allo.provider.model.adress.AdressDTO;
import br.forsign.allo.provider.model.adress.AdressInputDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AdressService {

    @Resource
    private AdressCreator creator;

    public AdressDTO create(AdressInputDTO inputDTO){
        return AdressConverter.toDTO(creator.create(inputDTO));
    }

}
