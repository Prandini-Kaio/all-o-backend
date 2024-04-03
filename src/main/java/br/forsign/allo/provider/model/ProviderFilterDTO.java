package br.forsign.allo.provider.model;

import br.forsign.allo.user.domain.TipoPessoa;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * Created By Kaio Prandini
 * Date: 3/16/24
 * Time: 3:22 PM
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProviderFilterDTO {

    @Nullable
    private Long id;

    @Nullable
    private String name;

    @Nullable
    private String description;


    @Nullable
    private TipoPessoa tipoPessoa;

}
