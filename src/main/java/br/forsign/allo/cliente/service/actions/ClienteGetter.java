package br.forsign.allo.cliente.service.actions;

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.repository.ClienteRepository;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import jakarta.annotation.Resource;
import lombok.extern.apachecommons.CommonsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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

    public ResponseEntity<org.springframework.core.io.Resource> getImageByName(String fileName){
        log.info(String.format("Consultando imagem do cliente %s.", fileName));

        try {
            // Monta o caminho completo da imagem com base no diretório configurado e no nome do arquivo
            Path filePath = Paths.get("src/main/resources/images-cliente").resolve(fileName).normalize();
            org.springframework.core.io.Resource resource = new UrlResource(filePath.toUri());

            // Verifica se o recurso existe e é acessível
            if (resource.exists() && resource.isReadable()) {


                // Retorna a resposta com o status OK e o recurso da imagem
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                // Caso a imagem não seja encontrada, retorna um status de não encontrado (404)
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            // Tratamento de erro se ocorrer uma URL malformada
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    public Cliente byUsernameId(String clienteUsername) {
        log.info(String.format("Consultando cliente pelo usuario %s.", clienteUsername));

        return repository.byUsernameId(clienteUsername);
    }
}
