package br.forsign.allo.cliente.service.actions;

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.repository.ClienteRepository;
import br.forsign.allo.cliente.service.actions.perfil.PerfilClienteUpdater;
import br.forsign.allo.common.utils.ImageUtils;
import br.forsign.allo.entidade.service.action.EnderecoUpdater;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.service.action.ProvedorGetter;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
@CommonsLog
public class ClienteUpdater {

    @Resource
    private ClienteRepository repository;

    @Resource
    private ClienteGetter getter;

    @Resource
    private ClienteValidator validator;

    @Resource
    private PerfilClienteUpdater perfilClienteUpdater;

    @Resource
    private ProvedorGetter provedorGetter;

    @Resource
    private EnderecoUpdater enderecoUpdater;

    public Cliente update(ClienteInput input){
        log.info(String.format("Atualizando cliente com ID %s.", input.getId()));

        validator.validarUpdate(input);

        Cliente cliente = getter.byId(input.getId());

        cliente.setNome(input.getNome());
        cliente.setCpfCnpj(input.getCpfCnpj());
        cliente.setEmail(input.getEmail());
        cliente.setTelefone(input.getTelefone());
        cliente.setEndereco(enderecoUpdater.update(input.getEnderecoInput()));
        cliente.setAtivo(true);

        perfilClienteUpdater.update(input);

        return repository.save(cliente);
    }

    public Cliente favoritar(String clienteUsername, Long idProvedor) {
        log.info(String.format("Favoritando provedor com ID %s para o cliente %s.", idProvedor, clienteUsername));

        Cliente cliente = getter.byUsernameId(clienteUsername);
        Provedor provedor = provedorGetter.byId(idProvedor);

        if(cliente.getProvedoresFavoritados().contains(provedor))
            cliente.getProvedoresFavoritados().remove(provedor);
        else
            cliente.getProvedoresFavoritados().add(provedor);

        return this.repository.save(cliente);
    }

    public String postImagemCliente(MultipartFile file) {
        log.info("Salvando imagem do cliente.");

        return ImageUtils.saveImageFile(file, "images-cliente");
    }
}
