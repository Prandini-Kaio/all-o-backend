package br.forsign.allo.provider.service.action.adress;

import br.forsign.allo.provider.domain.Adress;
import br.forsign.allo.provider.model.adress.AdressInputDTO;
import br.forsign.allo.provider.repository.AdressRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AdressCreator {

    @Resource
    private AdressRepository repository;

    public Adress create(AdressInputDTO input){
        Adress adress = new Adress();
        adress.setCEP(input.getCep());
        adress.setStreetname(input.getStreetname());
        adress.setNumber(input.getNumber());
        adress.setComplement(input.getComplement());

        return repository.save(adress);
    }
}
