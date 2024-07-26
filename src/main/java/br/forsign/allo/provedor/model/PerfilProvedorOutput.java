package br.forsign.allo.provedor.model;

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.avaliacao.model.AvaliacaoOutput;
import lombok.Data;

import java.util.List;

@Data
public class PerfilProvedorOutput {

    private Long id;

    private ProvedorOutput provedor;

    private AvaliacaoOutput avaliacao;

    private int servicosConcluidos;

    private float mediaAvaliacao;

    private int totalAvaliacoes;

    private String imagemPerfil;

    private String tempoCadastro;

    private String nome;

    private String email;

    private String descricao;

    private List<String> imagensServicos;
}
