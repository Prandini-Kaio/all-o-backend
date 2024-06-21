package br.forsign.allo.cliente.controller;

import br.forsign.allo.cliente.model.ClienteInput;
import br.forsign.allo.cliente.model.ClienteOuput;
import br.forsign.allo.cliente.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.hibernate.id.GUIDGenerator;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente")
public class ClienteController {

    @Resource
    private ClienteService service;

    @GetMapping
    @Operation(
            summary = "Consulta todos os clientes.",
            description = "Consulta todos os cientes cadastrador e ativos.")
    private ResponseEntity<List<ClienteOuput>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Consulta o cliente por id.",
            description = "Consulta o ciente cadastrado e ativo.")
    private ResponseEntity<ClienteOuput> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    @Operation(
            summary = "Cria um novo cliente.",
            description = "Cria um novo cliente e cadastra na base.")
    public ResponseEntity<ClienteOuput> create(@RequestBody @Valid ClienteInput input) {
        return ResponseEntity.ok().body(service.create(input));
    }

    @PutMapping
    @Operation(
            summary = "Atualiza um cliente",
            description = "Atualiza um cliente previamente cadastrado.")
    public ResponseEntity<ClienteOuput> update(@RequestBody @Valid ClienteInput inputDTO) {
        return ResponseEntity.ok().body(service.update(inputDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Desativa um cliente",
            description = "Desativa um cliente previamente cadastrado.")
    public ResponseEntity delById(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/favoritar")
    @Operation(
            summary = "Favorita um provedor",
            description = "Adiciona um provedor a lista de favoritos de um cliente.")
    public ResponseEntity<ClienteOuput> favoritar(
            @RequestParam Long idCliente,
            @RequestParam Long idProvedor
    ) {
        return ResponseEntity.ok().body(service.favoritar(idCliente, idProvedor));
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("image") MultipartFile file) {
        if (file.isEmpty()) {
            return "";
        }

        try {
            byte[] bytes = file.getBytes();

            String directoryPath = "images-cliente";
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs(); // Cria os diretórios se não existirem
            }

            UUID uuid = UUID.randomUUID();
            var nameImage = uuid.toString() + '.' + file.getContentType().split("/")[1];
            // Salva o arquivo no diretório especificado
            File uploadedFile = new File(directory, nameImage);
            try (FileOutputStream fos = new FileOutputStream(uploadedFile)) {
                fos.write(bytes);
            }

            return nameImage;
        } catch (IOException e) {
            return "erro";
        }
    }

    @GetMapping("/buscarImagem")
    public ResponseEntity<org.springframework.core.io.Resource> buscarImagemPorNome(@RequestParam String fileName) {
        try {
            // Monta o caminho completo da imagem com base no diretório configurado e no nome do arquivo
            Path filePath = Paths.get("images-cliente").resolve(fileName).normalize();
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
}
