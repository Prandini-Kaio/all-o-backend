package br.forsign.allo.cliente.converter;

import br.forsign.allo.cliente.domain.Document;
import br.forsign.allo.cliente.model.document.DocumentDTO;

public class DocumentConverter {

    public static DocumentDTO toDTO(Document documents){
        return new DocumentDTO(documents.getId(), documents.getCpfCnpj());
    }


}
