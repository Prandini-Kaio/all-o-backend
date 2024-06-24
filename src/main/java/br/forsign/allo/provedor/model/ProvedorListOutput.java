package br.forsign.allo.provedor.model;

import br.forsign.allo.provedor.domain.Provedor;
import lombok.Data;

@Data
public class ProvedorListOutput {

    private Long idProvedor;

    private String razaoSocial;

    private String observacao;

    private double mediaAvaliacao;

    private boolean favorito;

    private String imagemPerfil;

    public ProvedorListOutput(PerfilProvedorOutput perfil) {
        this.idProvedor = perfil.getProvedor().getId();
        this.razaoSocial = perfil.getProvedor().getRazaoSocial();
        this.observacao = "Próximo a você!";
        this.mediaAvaliacao = perfil.getMediaAvaliacao();
        this.favorito = false;
        this.imagemPerfil = perfil.getImagemPerfil();
    }
}
