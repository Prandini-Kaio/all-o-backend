package br.forsign.allo.document.service.action;

import br.forsign.allo.document.domain.Documents;
import br.forsign.allo.document.model.DocumentsDTO;
import br.forsign.allo.document.repository.DocumentRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import javax.swing.text.Document;

@Service
public class DocumentCreator {

    @Resource
    private DocumentRepository repository;


    public Documents create(DocumentsDTO documentsDTO){
        Documents documents = new Documents();
        documents.setCpf_cnpj(documentsDTO.getCpf_cnpj());
        return repository.save(documents);
    }
}
