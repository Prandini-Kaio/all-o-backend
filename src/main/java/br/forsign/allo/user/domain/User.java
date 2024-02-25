package br.forsign.allo.user.domain;

import br.forsign.allo.contact.domain.Contact;
import br.forsign.allo.document.domain.Documents;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "USUARIO")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "USER_NAME")
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Documents documents;
    @OneToOne(cascade = CascadeType.ALL)
    private Contact contacts;
}
