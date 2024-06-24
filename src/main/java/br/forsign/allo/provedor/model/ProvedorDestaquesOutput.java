package br.forsign.allo.provedor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProvedorDestaquesOutput {

    private Long id;

    private String razaoSocial;

    private String nomeProfissao;

    private String imagem;

}
