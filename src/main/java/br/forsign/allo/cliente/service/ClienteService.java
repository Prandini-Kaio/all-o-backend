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
import br.forsign.allo.importacao.exception.FileStorageException;
import br.forsign.allo.importacao.exception.FileStorageExceptionMessages;
import br.forsign.allo.usuario.domain.Usuario;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@CommonsLog
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

    public ClienteOuput findById(Long id){
        log.info(String.format("Iniciando consulta a um cliente com ID %s.", id));

        return this.mapper.toOutput(getter.byId(id));
    }

    public List<ClienteOuput> findAll(){
        log.info("Iniciando consulta a clientes ativos no sistema.");

        List<Cliente> clientes = getter.findAtivos();

        return clientes.stream().map(mapper::toOutput).toList();
    }

    public ClienteOuput create(ClienteCadastroInput input){
        log.info(String.format("Iniciando cadastro de um cliente com nome %s no sistema.", input.getCliente().getNome()));

        return this.mapper.toOutput(this.creator.create(input));
    }

    public ClienteOuput update(ClienteInput input){
        log.info(String.format("Iniciando update de um cliente com nome %s no sistema.", input.getNome()));

        return this.mapper.toOutput(updater.update(input));
    }

    public void delete(Long id){
        log.info(String.format("Iniciando desativação de cliente %s no sistema.", id));

        deleter.byId(id);
    }

    public ClienteOuput favoritar(Long idProvedor) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();

        log.info(String.format("Iniciando processo de favoritar provedor %s para o cliente %s.", idProvedor, usuario.getLogin()));

        return this.mapper.toOutput(this.updater.favoritar(usuario.getUsername(), idProvedor));
    }

    @Transactional
    public ResponseEntity<InputStreamResource> findImage(Long id) {
        log.info(String.format("Iniciando consulta de imagem de provedor [%s]", id));

        try{
            MultipartFile mfile = this.getter.findImage(id);

            if(mfile == null || mfile.isEmpty()){
                throw new FileStorageException(FileStorageExceptionMessages.erroCriandoArquivo("Arquivo inexistente"));
            }

            InputStreamResource resource = new InputStreamResource(mfile.getInputStream());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + mfile.getName() + "\"");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(mfile.getSize())
                    .body(resource);

        }catch (Exception e){
            log.error(e.getMessage());
            throw new FileStorageException(FileStorageExceptionMessages.erroCriandoArquivo(e.getMessage()));
        }
    }

    public String uploadImage(MultipartFile file, Long id){
        log.info("Iniciando cadastro de imagem no sistema.");

        return updater.uploadImage(file, id);
    }
}
