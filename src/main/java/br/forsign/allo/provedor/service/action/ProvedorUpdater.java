package br.forsign.allo.provedor.service.action;

import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.repository.ProvedorRepository;
import br.forsign.allo.provedor.service.ProvedorValidator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ProvedorUpdater {

    @Resource
    private ProvedorRepository repository;

    @Resource
    private ProvedorGetter getter;

    @Resource
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
