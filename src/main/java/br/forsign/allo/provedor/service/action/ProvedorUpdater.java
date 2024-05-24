package br.forsign.allo.provider.service.action;

import br.forsign.allo.provider.domain.Provedor;
import br.forsign.allo.provider.model.ProvedorInput;
import br.forsign.allo.provider.repository.ProvedorRepository;
import br.forsign.allo.provider.service.ProvedorValidator;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProvedorUpdater {

    @Resource
    private ProvedorRepository repository;

    @Autowired
    private ProvedorGetter getter;

    @Autowired
    private ProvedorValidator validator;

    public Provedor update(ProvedorInput input){
        validator.validarUpdate(input);

        Provedor provedor = getter.byId(input.getId());

        provedor.setRazaoSocial(input.getRazaoSocial());
        provedor.setTipoPessoa(input.getTipoPessoa());

        provedor.setEmail(input.getEmail());
        provedor.setTelefone(input.getTelefone());
        provedor.setCpfCnpj(input.getCpfCnpj());




        return repository.save(provedor);
    }

}
