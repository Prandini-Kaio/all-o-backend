package br.forsign.allo.cliente.model;

/*
 * @author prandini
 * created 5/24/24
 */

import lombok.Data;

@Data
public class PerfilClienteOutput {

    private Long id;

    private ClienteOuput cliente;

    private String pathToImage;

    private String nome;

    private String email;

    private String descricao;
}
