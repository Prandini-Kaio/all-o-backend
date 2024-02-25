package br.forsign.allo.document.service;

import br.forsign.allo.document.converter.DocumentConverter;
import br.forsign.allo.document.domain.Documents;
import br.forsign.allo.document.model.DocumentsDTO;
import br.forsign.allo.document.model.DocumentsInputDTO;
import br.forsign.allo.document.service.action.DocumentCreator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    @Resource
    private DocumentCreator creator;

    public DocumentsDTO create(DocumentsInputDTO input){
        return DocumentConverter.toDTO(creator.create(input));
    }
}
