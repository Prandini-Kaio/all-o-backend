package br.forsign.allo.provider.model.operationhour;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationHourDTO {
    private Long id;

    private LocalTime openHour;

    private LocalTime breakTime;

    private LocalTime breakReturn;

    private LocalTime closeHour;
}
