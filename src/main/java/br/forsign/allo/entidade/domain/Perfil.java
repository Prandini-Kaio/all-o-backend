package br.forsign.allo.entidade.domain;

/*
 * @author prandini
 * created 5/24/24
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@SequenceGenerator(name = "SEQ_PRFL", sequenceName = "SEQ_PRFL")
public class Perfil {

    @Id
    @GeneratedValue(generator = "SEQ_PRFL")
    private Long id;

    private String pathToImage;

    private String nome;

    private String email;
}
