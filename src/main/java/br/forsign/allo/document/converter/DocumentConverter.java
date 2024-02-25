package br.forsign.allo.document.converter;

import br.forsign.allo.document.domain.Documents;
import br.forsign.allo.document.model.DocumentsDTO;

public class DocumentConverter {

    public static DocumentsDTO toDTO(Documents documents){
        return new DocumentsDTO(documents.getId(), documents.getCpf_cnpj());
    }


}
