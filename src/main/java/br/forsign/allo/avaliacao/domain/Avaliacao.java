package br.forsign.allo.avaliacao.domain;

/*
 * @author prandini
 * created 5/26/24
 */

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "AVALIACAO")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "NOTA")
    private double qualidade;

    @Column(name = "AGILIDADE")
    private double agilidade;

    @Column(name = "PRECO")
    private double preco;

    @Column(name = "URI_LIST")
    @ElementCollection
    private List<String> uriImagens;
}
