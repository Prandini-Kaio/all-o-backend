package br.forsign.allo.user.model;

import br.forsign.allo.contact.model.ContactDTO;
import br.forsign.allo.contact.model.ContactInputDTO;
import br.forsign.allo.document.model.DocumentsDTO;
import br.forsign.allo.document.model.DocumentsInputDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInputDTO {
    @NotBlank
    @Schema(example = "Matheus Frangote")
    private String name;
    private DocumentsInputDTO documents;
    private ContactInputDTO contact;
}
