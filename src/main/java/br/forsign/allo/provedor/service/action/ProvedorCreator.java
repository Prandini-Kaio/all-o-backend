package br.forsign.allo.provedor.service.action;

import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.repository.ProvedorRepository;
import br.forsign.allo.provedor.service.PerfilProvedorService;
import br.forsign.allo.provedor.service.ProvedorValidator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProvedorCreator {

    @Resource
    private ProvedorRepository repository;

    @Resource
    private ProvedorValidator validator;

    @Resource
    private PerfilProvedorService perfilProvedorService;

    public Provedor create(ProvedorInput input){
        validator.validarCreate(input);

        Provedor provedor = new Provedor();

        provedor.setRazaoSocial(input.getRazaoSocial());
        provedor.setTipoPessoa(input.getTipoPessoa());
        provedor.setEmail(input.getEmail());
        provedor.setTelefone(input.getTelefone());
        provedor.setCpfCnpj(input.getCpfCnpj());
        provedor.setAtivo(true);
        provedor.setDtRegistro(LocalDate.now());

        repository.save(provedor);
        perfilProvedorService.create(provedor);
        return provedor;
    }
}
