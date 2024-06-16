package br.forsign.allo.profissao.model;

/*
 * @author prandini
 * created 5/25/24
 */

import lombok.Data;

@Data
public class ProfissaoOutput {

    private Long id;

    private String nomeIcone;

    private String nome;

    private ProfissaoCategoriaEnum categoria;
}
