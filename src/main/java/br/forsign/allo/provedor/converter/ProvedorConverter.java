package br.forsign.allo.provedor.converter;

import br.forsign.allo.entidade.converter.EnderecoMapper;
import br.forsign.allo.profissao.converter.ProfissaoMapper;
import br.forsign.allo.profissao.model.ProfissaoOutput;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorOutput;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProvedorConverter {

    @Resource
    private ProfissaoMapper profissaoMapper;

    @Resource
    private EnderecoMapper enderecoMapper;

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
}

