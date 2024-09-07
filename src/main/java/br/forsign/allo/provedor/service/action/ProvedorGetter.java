package br.forsign.allo.provedor.service.action;

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.common.utils.CommonExceptionSupplier;
import br.forsign.allo.profissao.converter.ProfissaoMapper;
import br.forsign.allo.provedor.converter.ProvedorConverter;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.provedor.model.ProvedorDestaquesOutput;
import br.forsign.allo.provedor.model.ProvedorFilter;
import br.forsign.allo.provedor.repository.ProvedorRepository;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
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

@Component
@CommonsLog
public class ProvedorGetter {

    @Resource
    private ProvedorRepository repository;

    public Page<Provedor> findAll(Pageable pageable) {
        log.info("Consultando todos os provedores.");

        return repository.findAtivos(pageable).orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor"));
    }

    public Provedor byId(Long id){
        log.info(String.format("Consultando provedor com ID %s.", id));

        return repository
                .findById(id)
                .orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor", id));
    }

    public Page<Provedor> findByFilter(String razaoSocial, String profissao, Pageable pageable){
        log.info(String.format("Consultando provedor pelo nome %s; profissão %s.", razaoSocial, profissao));

        return repository.byFilter(razaoSocial, profissao, pageable);
    }

    public List<Provedor> findByFilter(ProvedorFilter filter){
        log.info(String.format("Consultando provedor pelo nome"));

        return repository.byFilter(filter);
    }

    public Provedor byCpfCnpj(String cpfCnpj){
        log.info(String.format("Consultando provedor pelo CPF/CNPJ %s.", cpfCnpj));

        return repository.findByCpfCnpj(cpfCnpj).orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor", cpfCnpj));
    }

    public boolean existsByCpfCnpj(String cpfCnpj){
        log.info(String.format("Consultando a existência de provedor com base no CPF/CNPJ %s", cpfCnpj));

        Optional<Provedor> provedor = repository.findByCpfCnpj(cpfCnpj);

        return provedor.isPresent();
    }

    public boolean existsById(Long id){
        log.info(String.format("Consultando a existência de provedor com base no ID %s", id));

        Optional<Provedor> provedor = repository.findById(id);

        return provedor.isPresent();
    }

    public List<Provedor> byProfissao(Long idProfissao) {
        log.info(String.format("Consultando provedor com base no ID da profissão %s", idProfissao));

        return repository.findByProfissoesId(idProfissao);
    }

    public ResponseEntity<org.springframework.core.io.Resource> getImageByName(String fileName, String path){
        log.info(String.format("Consultando imagem de provedor com base no nome do arquivo %s", fileName));

        try {
            // Monta o caminho completo da imagem com base no diretório configurado e no nome do arquivo

            Path filePath = Paths.get("src/main/resources/" + path).resolve(fileName).normalize();

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

    public List<Provedor> getByHighAvaliacao() {
        log.info("Consulta profissionais em destaque.");

        return repository.findMelhoresAvaliacoes();
    }

    public List<Provedor> mostRelevant(){
        log.info("Consulta profissionais mais relevantes.");

        return repository.mostRelevant();
    }

    public Provedor byUsername(String username) {
        log.info(String.format("Consultando provedor com base no username %s", username));

        return repository.byUsername(username).orElseThrow(CommonExceptionSupplier.naoEncontrado("Provedor", username));
    }
}
