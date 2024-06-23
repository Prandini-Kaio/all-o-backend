package br.forsign.allo.cliente.service.actions;

import br.forsign.allo.cliente.domain.Cliente;
import br.forsign.allo.cliente.repository.ClienteRepository;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import jakarta.annotation.Resource;
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
public class ClienteGetter {

    @Resource
    private ClienteRepository repository;

    public List<Cliente> findAtivos(){
        return repository.findAtivos();
    }

    public Cliente byId(Long id){
        return repository.findById(id).orElseThrow(CommonExceptionSupplier.naoEncontrado("Cliente", id));
    }

    public Cliente byUsername(String username){
        return repository.findByUsername(username).orElseThrow(CommonExceptionSupplier.naoEncontrado("Cliente", username));
    }

    public Cliente byCpf(String cpf){
        return repository.findByCpf(cpf).orElseThrow(CommonExceptionSupplier.naoEncontrado("Cliente", cpf));
    }

    public boolean existsByCpf(String cpf){
        Optional<Cliente> cliente = repository.findByCpf(cpf);

        return cliente.isPresent();
    }

    public boolean existsById(Long id){
        Optional<Cliente> cliente = repository.findById(id);

        return cliente.isPresent();
    }

    public ResponseEntity<org.springframework.core.io.Resource> getImageByName(String fileName){
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
        return repository.byUsernameId(clienteUsername);
    }
}
