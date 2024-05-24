package br.forsign.allo.entidade.domain;

/*
 * @author prandini
 * created 5/22/24
 */

import br.forsign.allo.user.domain.Document;
import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public class Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;

}
