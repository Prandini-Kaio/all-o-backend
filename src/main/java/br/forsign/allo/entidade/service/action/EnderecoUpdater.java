package br.forsign.allo.entidade.service.action;

import br.forsign.allo.entidade.domain.Endereco;
import br.forsign.allo.entidade.model.EnderecoInput;
import br.forsign.allo.entidade.repository.EnderecoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class EnderecoUpdater {
    @Resource
    private EnderecoRepository repository;

    @Resource
    private EnderecoGetter getter;

    public Endereco update(EnderecoInput input){
        Endereco endereco = getter.byId(input.getId());

        endereco.setCep(input.getCep());
        endereco.setEstado(input.getEstado());
        endereco.setCidade(input.getCidade());
        endereco.setBairro(input.getBairro());
        endereco.setLogradouro(input.getLogradouro());
        endereco.setNumero(input.getNumero());

        return repository.save(endereco);
    }
}
