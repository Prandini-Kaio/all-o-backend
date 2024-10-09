package br.forsign.allo.provedor.domain;

import br.forsign.allo.avaliacao.domain.Avaliacao;
import br.forsign.allo.provedor.domain.Provedor;
import br.forsign.allo.entidade.domain.Perfil;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity(name = "PERFIL_PROVEDOR")
    public class PerfilProvedor extends Perfil {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        @JoinColumn(name = "PROVEDOR_ID")
        private Provedor provedor;

        @OneToOne
        @JoinColumn(name = "AVALIACAO_ID")
        private Avaliacao avaliacao;

        @ElementCollection
        @Column(name = "IMAGENS_SERVICOS")
        private List<String> imagensServicos;

        @Column(name = "SERVICOS")
        private int servicosConcluidos;

        @Column(name = "MEDIA_AVALIACAO")
        private double mediaAvaliacao;

        @Column(name = "TOTAL_AVALIACAO")
        private int totalAvaliacao;

        @Column(name = "TEMPO_CADASTRO")
        private String tempoCadastro;

        public void addImagemServico(String url){
            this.imagensServicos.add(url);
        }
    }


