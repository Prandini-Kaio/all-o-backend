package br.forsign.allo.user.service.actions.document;

import br.forsign.allo.user.converter.DocumentConverter;
import br.forsign.allo.user.model.document.DocumentsDTO;
import br.forsign.allo.user.model.document.DocumentsInputDTO;
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
