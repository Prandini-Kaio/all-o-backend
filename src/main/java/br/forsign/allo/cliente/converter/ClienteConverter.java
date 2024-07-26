package br.forsign.allo.cliente.converter;

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.model.ClienteOuput;

public class ClienteConverter {

    public static ClienteOuput toOutput(Cliente cliente){
        return ClienteOuput.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpfCnpj())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .ativo(cliente.isAtivo())
                .build();
    }
}
