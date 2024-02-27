package br.forsign.allo.user.model.document;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocumentsInputDTO {
    @Schema(example = "12345678900")
    private String cpfCnpj;
}
