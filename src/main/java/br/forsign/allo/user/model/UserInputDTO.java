package br.forsign.allo.user.model;

import br.forsign.allo.contact.model.ContactDTO;
import br.forsign.allo.document.model.DocumentsDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInputDTO {
    @NotBlank
    private String name;
    private DocumentsDTO documents;
    private ContactDTO contact;
}
