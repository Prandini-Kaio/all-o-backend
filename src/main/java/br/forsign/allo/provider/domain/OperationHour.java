package br.forsign.allo.provider.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "OPEN_HOUR")
     //Exemplo
    LocalTime openHour = LocalTime.now();

    //TODO -> esquema de manipulação de horário em actions
    //TODO -> Time gap (Horário de almoço)

    @Column(name = "CLOSE_HOUR")
     //Exemplo
    LocalTime closeHour = LocalTime.now();
}
