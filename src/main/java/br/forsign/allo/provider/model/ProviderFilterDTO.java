package br.forsign.allo.provider.model;

import br.forsign.allo.user.domain.TipoPessoa;
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
    private Long id;

    private String name;

    private String description;

    private String profession;

    private TipoPessoa tipoPessoa;

}
