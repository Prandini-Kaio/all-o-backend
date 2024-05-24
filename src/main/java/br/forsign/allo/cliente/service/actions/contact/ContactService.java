package br.forsign.allo.cliente.service.actions.contact;

import br.forsign.allo.cliente.converter.ContactConverter;
import br.forsign.allo.cliente.model.contact.ContactDTO;
import br.forsign.allo.cliente.model.contact.ContactInputDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Resource
    private ContactCreator creator;

    public ContactDTO create(ContactInputDTO inputDTO){
        return ContactConverter.toDTO(creator.create(inputDTO));
    }
}
