package br.forsign.allo.entidade.service.action;

import br.forsign.allo.entidade.domain.Endereco;
import br.forsign.allo.entidade.model.EnderecoInput;
import br.forsign.allo.entidade.repository.EnderecoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author kaiooliveira
 * created 16/06/2024
 */

@Component
public class EnderecoCreator {

    @Resource
    private EnderecoRepository repository;

    public Endereco create(EnderecoInput input){
        Endereco endereco = new Endereco();

        endereco.setCep(input.getCep());
        endereco.setEstado(input.getEstado());
        endereco.setCidade(input.getCidade());
        endereco.setBairro(input.getBairro());
        endereco.setLogradouro(input.getLogradouro());
        endereco.setNumero(input.getNumero());

        return repository.save(endereco);
    }
}
