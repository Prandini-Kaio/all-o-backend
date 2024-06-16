package br.forsign.allo.provedor.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilProvedorInput {

    @NotNull
    @Schema(description = "ID do provedor")
    private Long idProvedor;

    @Schema(description = "ID da avaliacao")
    private Long idAvaliacao;

    @Schema(description = "Descricao do perfil")
    private String descricao;

    @Schema(description = "Imagem")
    private String perfilImage;
}
