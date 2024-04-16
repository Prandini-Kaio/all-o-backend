package br.forsign.allo.provider.domain;

import br.forsign.allo.provider.model.operationhour.OperationHourDTO;
import br.forsign.allo.provider.model.operationhour.OperationHourInputDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "OPEN_HOUR")
    private LocalTime openHour;

    @Column(name = "BREAK_TIME")
    private LocalTime breakTime;

    @Column(name = "BREAK_RETURN")
    private LocalTime breakReturn;

    @Column(name = "CLOSE_HOUR")
    private LocalTime closeHour;

    public OperationHour(OperationHourInputDTO inputDTO){
        this.openHour = inputDTO.getOpenHour();
        this.breakTime = inputDTO.getBreakTime();
        this.breakReturn = inputDTO.getBreakReturn();
        this.closeHour = inputDTO.getCloseHour();
    }

    public OperationHourDTO toOutput(){
        return new OperationHourDTO(
                this.id,
                this.openHour,
                this.breakTime,
                this.breakReturn,
                this.closeHour
        );
    }
}
