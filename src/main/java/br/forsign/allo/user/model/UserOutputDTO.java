package br.forsign.allo.user.model;

import br.forsign.allo.user.model.contact.ContactDTO;
import br.forsign.allo.user.model.document.DocumentsDTO;
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
