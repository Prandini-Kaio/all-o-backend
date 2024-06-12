package br.forsign.allo.avaliacao.service.action;

/*
 * @author prandini
 * created 5/26/24
 */

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.avaliacao.model.AvaliacaoInput;
import br.forsign.allo.avaliacao.repository.AvaliacaoRepository;
import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.service.actions.ClienteGetter;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoCreator {

    @Resource
    private AvaliacaoRepository repository;


    @Resource
    private ProvedorGetter provedorGetter;

    @Resource
    private ClienteGetter clienteGetter;

    public Avaliacao avaliar(AvaliacaoInput input) {
        Avaliacao avaliacao = new Avaliacao();

        Provedor provedor = provedorGetter.byId(input.getProvedorId());
        Cliente cliente = clienteGetter.byId(input.getClienteId());

        avaliacao.setTitulo(input.getTitulo());
        avaliacao.setDescricao(input.getDescricao());
        avaliacao.setNota(input.getNota());
        avaliacao.setCliente(cliente);
        avaliacao.setProvedor(provedor);

        return repository.save(avaliacao);
    }
}
