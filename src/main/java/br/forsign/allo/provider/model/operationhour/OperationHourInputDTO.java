package br.forsign.allo.provider.model.operationhour;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OperationHourInputDTO {
    @Schema(example = "7:30")
    private LocalTime openHour;

    @Schema(example = "12:00")
    private LocalTime breakTime;

    @Schema(example = "14:00")
    private LocalTime breakReturn;

    @Schema(example = "20:30")
    private LocalTime closeHour;
}
