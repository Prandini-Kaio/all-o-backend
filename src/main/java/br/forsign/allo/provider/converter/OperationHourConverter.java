package br.forsign.allo.provider.converter;

import br.forsign.allo.provider.domain.OperationHour;
import br.forsign.allo.provider.model.operationhour.OperationHourDTO;

public class OperationHourConverter {
    public static OperationHourDTO toDTO(OperationHour operationHour){
        return new OperationHourDTO(
                operationHour.getId(),
                operationHour.getOpenHour(),
                operationHour.getBreakTime(),
                operationHour.getBreakReturn(),
                operationHour.getCloseHour());
    }
}
