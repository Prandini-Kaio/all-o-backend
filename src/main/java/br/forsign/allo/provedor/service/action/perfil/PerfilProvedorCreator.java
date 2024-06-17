package br.forsign.allo.provedor.service.action.perfil;

import br.forsign.allo.provedor.domain.PerfilProvedor;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorInput;
import br.forsign.allo.provedor.repository.PerfilProvedorRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class PerfilProvedorCreator {

    @Resource
    private PerfilProvedorRepository repository;

    public PerfilProvedor create(ProvedorInput input, Provedor provedor){
        PerfilProvedor perfilProvedor = new PerfilProvedor();

        perfilProvedor.setProvedor(provedor);
        perfilProvedor.setAvaliacao(null);
        perfilProvedor.setServicosConcluidos(0);
        perfilProvedor.setMediaAvaliacao(0);
        perfilProvedor.setTempoCadastro(0);
        perfilProvedor.setNome(provedor.getRazaoSocial());
        perfilProvedor.setEmail(provedor.getEmail());
        perfilProvedor.setDescricao("Bem vindo ao meu perfil!");

        perfilProvedor.setImagemPerfil(input.getPerfilImagem());
        perfilProvedor.setImagensServicos(input.getServicoImagens());

        return repository.save(perfilProvedor);

    }
}
