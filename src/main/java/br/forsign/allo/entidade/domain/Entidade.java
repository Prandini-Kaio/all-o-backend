package br.forsign.allo.entidade.domain;

/*
 * @author prandini
 * created 5/22/24
 */

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDate;

@Data
@MappedSuperclass
public class Entidade {

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;

    @Column(name = "DATA_REGISTRO")
    private LocalDate dtRegistro;

}
