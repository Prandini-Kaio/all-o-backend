package br.forsign.allo.provider.service;


import br.forsign.allo.provider.model.ProvedorInput;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
@CommonsLog
public class ProvedorValidator {

    public void validarCreate(ProvedorInput inputDTO){
        log.warn("TODO -> IMPLEMENTAR PROVIDER VALIDATOR");
    }

    public void validarUpdate() {
        log.warn("TODO -> IMPLEMENTAR PROVIDER VALIDATOR");
    }
}
