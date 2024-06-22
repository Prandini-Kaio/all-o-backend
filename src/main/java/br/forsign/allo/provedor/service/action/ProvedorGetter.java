package br.forsign.allo.provedor.service.action;

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.profissao.converter.ProfissaoMapper;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.repository.ProvedorRepository;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Data
@Getter
@Setter

@Component
public class ProvedorGetter {

    @Resource
    private ProvedorRepository repository;

    @Resource
    private ProfissaoMapper mapper;

    public Page<Provedor> findAll(Pageable pageable) {
        return repository.findAtivos(pageable).orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor"));
    }

    public Provedor byId(Long id){
        return repository
                .findById(id)
                .orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor", id));
    }

    public Page<Provedor> findByFilter(String razaoSocial, String profissao, Pageable pageable){
        return repository.byFilter(razaoSocial, profissao, pageable);
    }

    public Provedor byCpfCnpj(String cpfCnpj){
        return repository.findByCpfCnpj(cpfCnpj).orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor", cpfCnpj));
    }

    public boolean existsByCpfCnpj(String cpfCnpj){
        Optional<Provedor> provedor = repository.findByCpfCnpj(cpfCnpj);

        return provedor.isPresent();
    }

    public boolean existsById(Long id){
        Optional<Provedor> provedor = repository.findById(id);

        return provedor.isPresent();
    }

    public List<Provedor> byProfissao(Long idProfissao) {
        return repository.findByProfissoesId(idProfissao);
    }

    public int getTotalAvaliacao(List<Avaliacao> avaliacoes){
        return avaliacoes.size();
    }

    public ResponseEntity<org.springframework.core.io.Resource> getImageByName(String fileName){
        try {
            // Monta o caminho completo da imagem com base no diretório configurado e no nome do arquivo

            Path filePath = Paths.get("src/main/resources/images-provedor").resolve(fileName).normalize();

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
