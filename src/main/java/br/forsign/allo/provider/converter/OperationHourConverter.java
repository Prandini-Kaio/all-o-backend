package br.forsign.allo.provider.converter;

import br.forsign.allo.provider.domain.OperationHour;
import br.forsign.allo.provider.model.operationhour.OperationHourDTO;

public class OperationHourConverter {
    public static OperationHourDTO toDTO(OperationHour operationhour){
        return new OperationHourDTO(operationhour.getId(),
                operationhour.getOpenHour(),
                operationhour.getBreakTime(),
                operationhour.getBreakReturn(),
                operationhour.getCloseHour());
    }
}
