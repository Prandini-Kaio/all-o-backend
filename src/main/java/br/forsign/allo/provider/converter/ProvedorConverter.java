package br.forsign.allo.provider.converter;

import br.forsign.allo.provider.domain.Provedor;
import br.forsign.allo.provider.model.ProvedorOutput;

public class ProvedorConverter {

    public static ProvedorOutput toOutput(Provedor provedor){
        return ProvedorOutput.builder()
                .id(provedor.getId())
                .razaoSocial(provedor.getRazaoSocial())
                .tipoPessoa(provedor.getTipoPessoa())
                .cpfCnpj(provedor.getCpfCnpj())
                .telefone(provedor.getTelefone())
                .email(provedor.getEmail())
                .ativo(provedor.isAtivo())
                .build();
    }
}

