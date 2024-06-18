package br.forsign.allo.entidade.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author kaiooliveira
 * created 16/06/2024
 */

@Data
@Entity
@Table(name = "ENDERECO")
@SequenceGenerator(name = "SEQ_ENDR", sequenceName = "SEQ_ENDR")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String cep;

    @NotNull
    private String estado;

    private String cidade;

    private String bairro;

    private String logradouro;

    private String numero;

}
