package br.forsign.allo.user.model;

import br.forsign.allo.contact.model.ContactDTO;
import br.forsign.allo.document.model.DocumentsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOutputDTO {
    private Long id;
    private String name;
    private ContactDTO contact;
    private DocumentsDTO documents;
}
