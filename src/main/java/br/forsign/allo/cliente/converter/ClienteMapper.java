package br.forsign.allo.cliente.converter;

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.model.ClienteOuput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClienteMapper {

    @Mapping(target = "favoritos", source = "provedoresFavoritados")
    ClienteOuput toOutput(Cliente cliente);

    Cliente toCliente(ClienteOuput clienteOuput);
}
