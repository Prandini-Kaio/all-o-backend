package br.forsign.allo.provedor.model;

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.avaliacao.model.AvaliacaoOutput;
import lombok.Data;

@Data
public class PerfilProvedorOutput {

    private Long id;

    private ProvedorOutput provedor;

    private AvaliacaoOutput avaliacao;

    private int servicosConcluidos;

    private float mediaAvaliacao;

    private String imagemPerfil;

    private int tempoCadastro;

    private String nome;

    private String email;

    private String descricao;
}
