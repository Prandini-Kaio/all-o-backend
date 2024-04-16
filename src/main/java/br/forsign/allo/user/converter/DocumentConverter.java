package br.forsign.allo.user.converter;

import br.forsign.allo.user.domain.Document;
import br.forsign.allo.user.model.document.DocumentDTO;

public class DocumentConverter {

    public static DocumentDTO toDTO(Document documents){
        return new DocumentDTO(documents.getId(), documents.getCpfCnpj());
    }


}
