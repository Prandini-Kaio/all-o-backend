package br.forsign.allo.provedor.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilProvedorInput {

    @NotNull
    @Schema(description = "ID do provedor")
    private Long idProvedor;

    @Schema(description = "Descricao do perfil", example = "Sou um provedor de serviços")
    private String descricao;
}
