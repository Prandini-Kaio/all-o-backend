package br.forsign.allo.cliente.domain;

/*
 * @author prandini
 * created 5/24/24
 */

import br.forsign.allo.entidade.domain.Perfil;
import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity(name = "PERFIL_CLIENTE")
@EqualsAndHashCode(callSuper = true)
public class PerfilCliente extends Perfil {

    @OneToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;
}
