package br.forsign.allo.user.service.actions.document;

import br.forsign.allo.user.domain.Document;
import br.forsign.allo.user.model.document.DocumentInputDTO;
import br.forsign.allo.user.repository.DocumentRepository;
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
