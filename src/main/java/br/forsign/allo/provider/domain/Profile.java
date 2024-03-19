package br.forsign.allo.provider.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO")
    private String description;

    @Column(name = "CERTIFICACAO")
    private String certification;

    @Column(name = "ESPECIFICACAO")
    private String specification;

    @Column(name = "EXPERIENCIA")
    private String experience;

}