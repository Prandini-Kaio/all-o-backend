package br.forsign.allo.user.converter;

import br.forsign.allo.user.domain.Document;
import br.forsign.allo.user.model.document.DocumentsDTO;

public class DocumentConverter {

    public static DocumentsDTO toDTO(Document documents){
        return new DocumentsDTO(documents.getId(), documents.getCpf_cnpj());
    }


}
