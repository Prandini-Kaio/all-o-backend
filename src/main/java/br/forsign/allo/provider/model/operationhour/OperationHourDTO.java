package br.forsign.allo.provider.model.operationhour;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationHourDTO {
    private Long id;

    private LocalDateTime openHour;

    private LocalDateTime breakTime;

    private LocalDateTime breakReturn;

    private LocalDateTime closeHour;
}