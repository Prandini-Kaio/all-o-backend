package br.forsign.allo.provider.domain;

import br.forsign.allo.domain.Entidade;
import br.forsign.allo.user.domain.TipoPessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROVEDOR")
@SequenceGenerator(name = "SEQ_PROVIDER")
public class Provider extends Entidade {

    @Enumerated
    private TipoPessoa tipoPessoa;
    
}


