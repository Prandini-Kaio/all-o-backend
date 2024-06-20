package br.forsign.allo.cliente.converter;

import br.forsign.allo.cliente.domain.PerfilCliente;
import br.forsign.allo.cliente.model.PerfilClienteOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PerfilClienteMapper {

    @Mapping(target = "cliente.cpf", source = "cliente.cpfCnpj")
    PerfilClienteOutput toOutput(PerfilCliente perfilCliente);

    PerfilCliente fromOutput(PerfilClienteOutput perfilClienteOutput);
}
