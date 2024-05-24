package br.forsign.allo.provedor.service.action;

import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.repository.ProvedorRepository;
import br.forsign.allo.provedor.service.ProvedorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProvedorCreator {

    @Autowired
    private ProvedorRepository repository;

    @Autowired
    private ProvedorValidator validator;

    public Provedor create(ProvedorInput input){
        validator.validarCreate(input);

        Provedor provedor = new Provedor();

        provedor.setRazaoSocial(input.getRazaoSocial());
        provedor.setTipoPessoa(input.getTipoPessoa());
        provedor.setEmail(input.getEmail());
        provedor.setTelefone(input.getTelefone());
        provedor.setCpfCnpj(input.getCpfCnpj());
        provedor.setAtivo(true);

        return repository.save(provedor);
    }
}
