package br.forsign.allo.user.domain;

import br.forsign.allo.contact.domain.Contacts;
import br.forsign.allo.document.domain.Documents;
import jakarta.persistence.*;

@Entity
@Table(name = "USUARIO")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USER_NAME")
    private String name;
    @OneToOne
    private Documents documents;
    @OneToOne
    private Contacts contacts;
}
