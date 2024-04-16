package br.forsign.allo.provider.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adress {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "CEP")
    private String CEP; //TODO -> Fazer um tratamento correto de CEP

    @Column(name = "NOME_RUA")
    private String streetname;

    @Column(name = "NUMERO")
    private int number;

    @Column(name = "COMPLEMENTO")
    private String complement;

}
