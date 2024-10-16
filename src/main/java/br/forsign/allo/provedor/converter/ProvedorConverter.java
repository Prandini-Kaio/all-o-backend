package br.forsign.allo.provedor.converter;

import br.forsign.allo.entidade.converter.EnderecoMapper;
import br.forsign.allo.profissao.converter.ProfissaoMapper;
import br.forsign.allo.profissao.model.ProfissaoOutput;
import br.forsign.allo.provedor.domain.PerfilProvedor;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorDestaquesOutput;
import br.forsign.allo.provedor.model.ProvedorOutput;
import br.forsign.allo.provedor.service.action.perfil.PerfilProvedorGetter;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProvedorConverter {

    @Resource
    private ProfissaoMapper profissaoMapper;

    @Resource
    private EnderecoMapper enderecoMapper;

    @Resource
    private PerfilProvedorGetter getter;

    public ProvedorOutput toOutput(Provedor provedor){

        ProfissaoOutput profissao = profissaoMapper.toOutput(provedor.getProfissao());

        return ProvedorOutput.builder()
                .id(provedor.getId())
                .razaoSocial(provedor.getRazaoSocial())
                .tipoPessoa(provedor.getTipoPessoa())
                .cpfCnpj(provedor.getCpfCnpj())
                .telefone(provedor.getTelefone())
                .profissao(profissao)
                .endereco(enderecoMapper.toOutput(provedor.getEndereco()))
                .email(provedor.getEmail())
                .ativo(provedor.isAtivo())
                .build();
    }

    public ProvedorDestaquesOutput toDestaqueOutput(Provedor provedor){
        ProvedorDestaquesOutput provedorDestaque = new ProvedorDestaquesOutput();
        PerfilProvedor perfilProvedor = getter.byProvedorId(provedor.getId());

        provedorDestaque.setRazaoSocial(provedor.getRazaoSocial());
        provedorDestaque.setId(provedor.getId());
        provedorDestaque.setNomeProfissao(provedor.getProfissao().getNome());
        provedorDestaque.setRazaoSocial(provedor.getRazaoSocial());
        provedorDestaque.setImagem(perfilProvedor.getImagemPerfil());
        return provedorDestaque;
    }

    public List<ProvedorDestaquesOutput> toDestaqueOutput(List<Provedor> provedores){
        return provedores.stream().map(this::toDestaqueOutput).toList();
    }
}

