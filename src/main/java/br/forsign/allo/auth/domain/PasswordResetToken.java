package br.forsign.allo.auth.domain;

import br.forsign.allo.usuario.domain.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author kaiooliveira
 * created 01/07/2024
 */

@Data
@Entity
@NoArgsConstructor
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TOKEN")
    private String token;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    private LocalDateTime dataExpiracao;

    public PasswordResetToken(Usuario usuario, String token){
        this.usuario = usuario;
        this.token = token;
        this.dataExpiracao = LocalDateTime.now().plusHours(1);
    }
}
