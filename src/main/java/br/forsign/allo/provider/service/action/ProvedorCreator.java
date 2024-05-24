package br.forsign.allo.provider.service.action;

import br.forsign.allo.provider.domain.Provedor;
import br.forsign.allo.provider.model.ProvedorInput;
import br.forsign.allo.provider.repository.ProvedorRepository;
import br.forsign.allo.provider.service.ProvedorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProviderCreator {

    @Autowired
    private ProvedorRepository repository;

    @Autowired
    private ProvedorValidator validator;


    public Provedor create(ProvedorInput input){
        validator.validarCreate(input);

        Provedor provedor = new Provedor();

        provedor.setRazaoSocial(input.getRazaoSocial());
        provedor.setTipoPessoa(input.getTipoPessoa());
        provedor.setEmail(input.getEntidade().getEmail());
        provedor.setTelefone(input.getEntidade().getTelefone());
        provedor.setCpfCnpj(input.getEntidade().getCpfCnpj());

        return repository.save(provedor);
    }
}
