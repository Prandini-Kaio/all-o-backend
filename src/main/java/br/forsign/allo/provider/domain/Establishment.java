package br.forsign.allo.provider.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Establishment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ESTABLISHMENT_NAME")
    private String name;

    //TODO -> Fazer um campo correto de tratamento de endereços.
    //@Column(name = "ADRESS")
    //private String adress;

}
