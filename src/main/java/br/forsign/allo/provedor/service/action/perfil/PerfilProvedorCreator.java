package br.forsign.allo.provedor.service.action.perfil;

import br.forsign.allo.provedor.domain.PerfilProvedor;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.PerfilProvedorInput;
import br.forsign.allo.provedor.repository.PerfilProvedorRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class PerfilProvedorCreator {

    @Resource
    private PerfilProvedorRepository repository;

    public PerfilProvedor create(Provedor provedor, PerfilProvedorInput input) {
        PerfilProvedor perfilProvedor = new PerfilProvedor();

        perfilProvedor.setProvedor(provedor);
        perfilProvedor.setAvaliacao(null);
        perfilProvedor.setServicosConcluidos(0);
        perfilProvedor.setMediaAvaliacao(0);
        perfilProvedor.setTempoCadastro(0);
        perfilProvedor.setImagemPerfil(input.getPerfilImage());
        perfilProvedor.setNome(provedor.getRazaoSocial());
        perfilProvedor.setEmail(provedor.getEmail());
        perfilProvedor.setDescricao(input.getDescricao());

        return repository.save(perfilProvedor);

    }
}
