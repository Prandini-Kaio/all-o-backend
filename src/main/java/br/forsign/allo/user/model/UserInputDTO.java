package br.forsign.allo.user.model;

import br.forsign.allo.user.model.contact.ContactInputDTO;
import br.forsign.allo.user.model.document.DocumentInputDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInputDTO {
    private Long id;
    @Schema(example = "Matheus Frangote")
    private String name;
    @Valid
    private DocumentInputDTO document;
    @Valid
    private ContactInputDTO contact;
}
