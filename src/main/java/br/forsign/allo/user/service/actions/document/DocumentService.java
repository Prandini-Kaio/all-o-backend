package br.forsign.allo.user.service.actions.document;

import br.forsign.allo.user.converter.DocumentConverter;
import br.forsign.allo.user.model.document.DocumentDTO;
import br.forsign.allo.user.model.document.DocumentInputDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    @Resource
    private DocumentCreator creator;
  
    private DocumentValidator validator;

    public DocumentDTO create(DocumentInputDTO input){
        validator.validarDocumentos(input);
        return DocumentConverter.toDTO(creator.create(input));
    }
}
