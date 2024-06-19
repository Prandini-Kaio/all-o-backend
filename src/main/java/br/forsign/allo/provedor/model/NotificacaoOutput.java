package br.forsign.allo.provedor.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Data
public class NotificacaoOutput {

    private String titulo;

    private String mensagem;

    private LocalDate dataCriacao;

    private boolean visualizada;

    private ProvedorOutput provedor;

}
