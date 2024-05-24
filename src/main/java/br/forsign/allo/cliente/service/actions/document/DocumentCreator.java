package br.forsign.allo.cliente.service.actions.document;

import br.forsign.allo.cliente.domain.Document;
import br.forsign.allo.cliente.model.document.DocumentInputDTO;
import br.forsign.allo.cliente.repository.DocumentRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DocumentCreator {

    @Resource
    private DocumentRepository repository;


    public Document create(DocumentInputDTO inputDTO){
        Document documents = new Document();
        documents.setCpfCnpj(inputDTO.getCpfCnpj());
        return repository.save(documents);
    }
}
