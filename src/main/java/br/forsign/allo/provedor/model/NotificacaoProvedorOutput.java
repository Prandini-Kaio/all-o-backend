package br.forsign.allo.provedor.model;

import lombok.Data;

/**
 * @author kaiooliveira
 * created 25/06/2024
 */

@Data
public class NotificacaoProvedorOutput {

    private String nomeCliente;

    private String mensagem;

    private String dtRegistro;
}
