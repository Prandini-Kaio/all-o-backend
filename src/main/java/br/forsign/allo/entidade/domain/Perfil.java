package br.forsign.allo.entidade.domain;

/*
 * @author prandini
 * created 5/24/24
 */

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Perfil {

    private String pathToImage;

    private String nome;

    private String email;
}
