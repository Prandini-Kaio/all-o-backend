package br.forsign.allo.user.domain;

import br.forsign.allo.contact.domain.Contacts;
import br.forsign.allo.document.domain.Documents;
import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USER_NAME")
    private String name;
    @OneToOne
    @Column(name = "DOCUMENTS_ID")
    private Documents documents;
    @OneToOne
    @Column(name = "CONTACTS_ID")
    private Contacts contacts;
}
