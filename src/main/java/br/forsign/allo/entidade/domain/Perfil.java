package br.forsign.allo.entidade.domain;

/*
 * @author prandini
 * created 5/24/24
 */

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Perfil {

    @Column(name = "IMAGEM_PERFIL")
    private String imagemPerfil;

    @Column(name = "DESCRICAO")
    private String descricao;
}
