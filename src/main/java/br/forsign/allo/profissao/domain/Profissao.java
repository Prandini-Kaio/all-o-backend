package br.forsign.allo.profissao.domain;

/*
 * @author prandini
 * created 5/25/24
 */

import br.forsign.allo.profissao.model.ProfissaoCategoriaEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@Table(name = "PROFISSAO")
@SequenceGenerator(name = "SEQ_PRFS", sequenceName =  "SEQ_PRFS")
public class Profissao {

    @Id
    @GeneratedValue(generator = "SEQ_PRFS")
    private Long id;

    private String nome;

    private String descricao;

    private ProfissaoCategoriaEnum categoria;

    private String palavrasChave;

    private boolean ativo;
}
