package br.forsign.allo.provider.model.operationhour;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationHourDTO {
    private Long id;
    private String openHour;
    private String breakTime;
    private String breakReturn;
    private String closeHour;
}
