package br.forsign.allo.cliente.service;

import br.forsign.allo.cliente.converter.ClienteMapper;
import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.model.ClienteCadastroInput;
import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.model.ClienteOuput;
import br.forsign.allo.cliente.service.actions.ClienteCreator;
import br.forsign.allo.cliente.service.actions.ClienteDeleter;
import br.forsign.allo.cliente.service.actions.ClienteGetter;
import br.forsign.allo.cliente.service.actions.ClienteUpdater;
import br.forsign.allo.usuario.domain.Usuario;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ClienteService {

    @Resource
    private ClienteCreator creator;

    @Resource
    private ClienteGetter getter;

    @Resource
    private ClienteDeleter deleter;

    @Resource
    private ClienteUpdater updater;

    @Resource
    private ClienteMapper mapper;

    private final Logger logger = LoggerFactory.getLogger(ClienteGetter.class);

    public ClienteOuput findById(Long id){
        logger.info("Iniciando consulta a um cliente com ID {}.", id);

        return this.mapper.toOutput(getter.byId(id));
    }

    public List<ClienteOuput> findAll(){
        logger.info("Iniciando consulta a clientes ativos no sistema.");

        List<Cliente> clientes = getter.findAtivos();

        return clientes.stream().map(mapper::toOutput).toList();
    }

    public ClienteOuput create(ClienteCadastroInput input){
        logger.info("Iniciando cadastro de um cliente com nome {} no sistema.", input.getCliente().getNome());

        return this.mapper.toOutput(this.creator.create(input));
    }

    public ClienteOuput update(ClienteInput input){
        logger.info("Iniciando update de um cliente com nome {} no sistema.", input.getNome());

        return this.mapper.toOutput(updater.update(input));
    }

    public void delete(Long id){
        logger.info("Deletando cliente no sistema.");

        deleter.byId(id);
    }

    public ClienteOuput favoritar(Long idProvedor) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (Usuario) authentication.getPrincipal();

        return this.mapper.toOutput(this.updater.favoritar(userDetails.getUsername(), idProvedor));
    }

    public ResponseEntity<org.springframework.core.io.Resource> getImage(String filename){
        return getter.getImageByName(filename);
    }

    public String postImageCliente(MultipartFile file){
        return updater.postImagemCliente(file);
    }
}
