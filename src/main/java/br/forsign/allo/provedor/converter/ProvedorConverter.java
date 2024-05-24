package br.forsign.allo.provedor.converter;

import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorOutput;

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

