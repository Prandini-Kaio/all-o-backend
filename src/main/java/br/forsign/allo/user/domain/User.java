package br.forsign.allo.user.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "USUARIO")
@Data
@SequenceGenerator(name = "SEQ_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Document document;

    @OneToOne(cascade = CascadeType.ALL)
    private Contact contact;
}
