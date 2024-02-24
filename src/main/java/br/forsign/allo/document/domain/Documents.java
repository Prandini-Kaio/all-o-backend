package br.forsign.allo.document.domain;

import jakarta.persistence.*;

@Entity
public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CPF/CNPJ")
    private String cpf_cnpj;

}
