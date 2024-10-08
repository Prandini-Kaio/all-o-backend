package br.forsign.allo.cliente.service.actions;

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.domain.PerfilCliente;
import br.forsign.allo.cliente.repository.ClienteRepository;
import br.forsign.allo.cliente.service.actions.perfil.PerfilClienteGetter;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.importacao.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Component
@CommonsLog
public class ClienteGetter {

    @Resource
    private ClienteRepository repository;

    @Resource
    private PerfilClienteGetter perfilClienteGetter;

    @Resource
    private StorageService storageService;

    public List<Cliente> findAtivos(){
        return repository.findAtivos();
    }

    public Cliente byId(Long id){
        log.info(String.format("Consultando um cliente no sistema pelo ID %s.", id));

        return repository.findById(id).orElseThrow(CommonExceptionSupplier.naoEncontrado("Cliente", id));
    }

    public Cliente byUsername(String username){
        log.info(String.format("Consultando um cliente no sistema pelo usernam %s.", username));

        return repository.findByUsername(username).orElseThrow(CommonExceptionSupplier.naoEncontrado("Cliente", username));
    }

    public Cliente byCpf(String cpf){
        log.info(String.format("Consultando um cliente no sistema pelo CPF \"%s\".", cpf));

        return repository.findByCpf(cpf).orElseThrow(CommonExceptionSupplier.naoEncontrado("Cliente", cpf));
    }

    public boolean existsByCpf(String cpf){
        log.info(String.format("Consultando existência de cliente pelo CPF \"%s\".", cpf));

        Optional<Cliente> cliente = repository.findByCpf(cpf);

        return cliente.isPresent();
    }

    public boolean existsById(Long id){
        Optional<Cliente> cliente = repository.findById(id);

        return cliente.isPresent();
    }

    public MultipartFile findImage(Long id){
        log.info(String.format("Bucando imagem de perfil do cliente %s", id));
        PerfilCliente perfilCliente = perfilClienteGetter.byClienteId(id);

        return storageService.download(perfilCliente.getImagemPerfil());
    }

    public Cliente byUsernameId(String clienteUsername) {
        log.info(String.format("Consultando cliente pelo usuario %s.", clienteUsername));

        return repository.byUsernameId(clienteUsername);
    }
}
