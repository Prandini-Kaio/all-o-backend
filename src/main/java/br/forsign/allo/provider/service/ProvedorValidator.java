package br.forsign.allo.provider.service;


import br.forsign.allo.provider.model.ProvedorInput;
import br.forsign.allo.user.service.actions.UserGetter;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
public class ProviderValidator {

    @Resource
    private UserGetter userGetter;

    public void validarProviderCreate(ProvedorInput inputDTO){
        log.warn("TODO -> IMPLEMENTAR PROVIDER VALIDATOR");
    }
}
