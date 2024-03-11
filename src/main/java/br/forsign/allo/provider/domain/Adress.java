package br.forsign.allo.provider.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adress {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ENDERECO")
    private String name;

    //TODO -> Fazer um campo correto de tratamento de endereços.
    //@Column(name = "ADRESS")
    //private String adress;

}
