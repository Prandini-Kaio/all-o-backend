package br.forsign.allo.entidade.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */

@Data
@MappedSuperclass
public class Notificacao {

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "MENSAGEM")
    private String mensagem;

    @Column(name = "VISUALIZADA")
    private boolean visualizada;

    @Column(name = "DATA_CRIACAO")
    private LocalDate dataCriacao;
}
