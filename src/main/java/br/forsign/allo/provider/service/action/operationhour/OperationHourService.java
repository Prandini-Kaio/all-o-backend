package br.forsign.allo.provider.service.action.operationhour;

import br.forsign.allo.provider.converter.OperationHourConverter;
import br.forsign.allo.provider.model.operationhour.OperationHourDTO;
import br.forsign.allo.provider.model.operationhour.OperationHourInputDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class OperationHourService {

    @Resource
    private OperationHourCreator creator;

    public OperationHourDTO create(OperationHourInputDTO inputDTO){
        return OperationHourConverter.toDTO(creator.create(inputDTO));
    }
}
