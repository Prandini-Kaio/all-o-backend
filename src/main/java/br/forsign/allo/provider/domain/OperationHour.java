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

    //TODO -> esquema de manipulação de horário em actions

    @Column(name = "OPEN_HOUR")
    private LocalTime openHour;

    @Column(name = "BREAK_TIME")
    private LocalTime breakTime;

    @Column(name = "BREAK_RETURN")
    private LocalTime breakReturn;

    @Column(name = "CLOSE_HOUR")
    private LocalTime closeHour;
}
