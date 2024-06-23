package br.forsign.allo.cliente.service.actions;

import br.forsign.allo.auth.service.AuthService;
import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.model.ClienteCadastroInput;
import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.repository.ClienteRepository;
import br.forsign.allo.cliente.service.PerfilClienteService;
import br.forsign.allo.common.utils.CpfCnpjUtils;
import br.forsign.allo.entidade.service.action.EnderecoCreator;
import br.forsign.allo.usuario.domain.Usuario;
import br.forsign.allo.usuario.domain.UsuarioRole;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;

@Component
public class ClienteCreator {

    @Resource
    private ClienteRepository repository;

    @Resource
    private ClienteValidator validator;

    @Resource
    private PerfilClienteService perfilClienteService;

    @Resource
    private EnderecoCreator enderecoCreator;

    @Resource
    private AuthService authService;

    private final Logger logger = LoggerFactory.getLogger(ClienteCreator.class);

    public Cliente create(ClienteCadastroInput clienteInput){
        logger.info("Criando um novo cliente {}.", clienteInput.getCliente().getNome());

        ClienteInput input = clienteInput.getCliente();

        validator.validarCreate(input);

        Cliente cliente = new Cliente();

        clienteInput.getUsuario().setRole(UsuarioRole.CLIENTE);

        cliente.setNome(input.getNome());
        cliente.setEmail(input.getEmail());
        cliente.setTelefone(input.getTelefone());
        cliente.setEndereco(enderecoCreator.create(input.getEnderecoInput()));
        cliente.setCpfCnpj(CpfCnpjUtils.removeMascara(input.getCpfCnpj()));
        cliente.setProvedoresFavoritados(new HashSet<>());
        cliente.setAtivo(true);
        cliente.setDtRegistro(LocalDate.now());

        cliente.setUsuario((Usuario) this.authService.register(clienteInput.getUsuario()));

        this.repository.save(cliente);

        this.perfilClienteService.create(input, cliente);

        return cliente;
    }
}
