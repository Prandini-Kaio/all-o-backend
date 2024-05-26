package br.forsign.allo.profissao.domain;

/*
 * @author prandini
 * created 5/25/24
 */

import br.forsign.allo.profissao.model.ProfissaoCategoriaEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "PROFISSAO")
public class Profissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private String nomeIcone;

    @Enumerated
    private ProfissaoCategoriaEnum categoria;

    @ElementCollection
    private Set<String> palavrasChave;

    private boolean ativo;
}
