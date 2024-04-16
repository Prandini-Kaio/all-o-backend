package br.forsign.allo.provider.service.action.operationhour;

import br.forsign.allo.provider.domain.OperationHour;
import br.forsign.allo.provider.model.operationhour.OperationHourInputDTO;
import br.forsign.allo.provider.repository.OperationHourRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class OperationHourCreator {

    @Resource
    private OperationHourRepository repository;

    public OperationHour create(OperationHourInputDTO input){
        OperationHour operationHour = new OperationHour();
        operationHour.setOpenHour(input.getOpenHour());
        operationHour.setBreakTime(input.getBreakTime());
        operationHour.setBreakReturn(input.getBreakReturn());
        operationHour.setCloseHour(input.getCloseHour());

        return repository.save(operationHour);
    }
}
